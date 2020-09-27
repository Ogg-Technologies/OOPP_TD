package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import model.game.projectile.Projectile;
import utils.Vector;

public class Mock {
    public static Enemy createMockEnemy(Vector pos) {
        return new Enemy() {
            @Override
            public void update() {
            }

            @Override
            public Vector getPos() {
                return pos;
            }

            @Override
            public void accept(EnemyVisitor visitor) {
            }

            @Override
            public void damage(int amount) {
            }

            @Override
            public void applyStatusEffect(StatusEffect effect) {

            }

            @Override
            public Health getHealth() {
                return null;
            }
        };
    }

    public static Projectile createStationaryProjectile(Vector pos) {
        return new Projectile() {
            @Override
            public Vector getPosition() {
                return pos;
            }

            @Override
            public void update() {
            }

            @Override
            public boolean isConsumed() {
                return false;
            }
        };
    }
}
