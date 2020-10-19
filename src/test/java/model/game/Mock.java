package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyFactory;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import model.game.projectile.Projectile;
import model.game.tower.towerutils.EnemyTargeter;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mock {

    public static EnemyFactory createMockEnemyFactory() {
        List<Vector> path = new ArrayList<>();
        path.add(new Vector(0, 0));
        path.add(new Vector(1, 0));
        return new EnemyFactory(amount -> {
        }, path);
    }

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
                return new MutableHealth(1);
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

            @Override
            public void onEnemyHit(Enemy enemy) {
            }
        };
    }

    public static EnemyTargeter createMockEnemyTargeter() {
        Collection<Enemy> enemies = new ArrayList<>();
        enemies.add(Mock.createMockEnemy(new Vector(0, 0)));

        return new EnemyTargeter(() -> enemies);
    }
}
