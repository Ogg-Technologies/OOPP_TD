package model.game.enemy.concreteenemies;

import model.game.enemy.AbstractEnemy;
import model.game.enemy.BaseDamager;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.PathIterator;

public class BasicEnemy extends AbstractEnemy {
    public static final int BASE_HEALTH = 20;
    public static final double BASE_SPEED = 0.01;
    public final Type type;

    public BasicEnemy(BaseDamager baseDamager, PathIterator pathIterator, Type type) {
        super(baseDamager, pathIterator, (int) (BASE_HEALTH * type.healthMultiplier), BASE_SPEED * type.speedMultiplier);
        this.type = type;
    }

    public enum Type {
        FISHSTICK(1, 1),
        SWORDFISH(1.5, 1.1),
        FISH_AND_CHIPS(2, 1.2),
        FISH_IN_A_BOAT(3, 1.3),
        SAILFISH(1, 3.5),
        SHARK(5, 1.7),
        FISH_IN_FISH_TANK(10, 0.9);


        private double healthMultiplier;
        private double speedMultiplier;

        Type(double healthMultiplier, double speedMultiplier) {

            this.healthMultiplier = healthMultiplier;
            this.speedMultiplier = speedMultiplier;
        }
    }

    @Override
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
    }
}
