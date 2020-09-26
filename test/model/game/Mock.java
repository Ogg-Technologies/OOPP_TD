package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import model.game.projectile.Projectile;
import utils.VectorD;

public class Mock {
    public static Enemy createMockEnemy(VectorD pos) {
        return new Enemy() {
            @Override
            public void update() {
            }

            @Override
            public VectorD getPos() {
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

    public static Projectile createStationaryProjectile(VectorD pos) {
        return new Projectile() {
            @Override
            public VectorD getPosition() {
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
