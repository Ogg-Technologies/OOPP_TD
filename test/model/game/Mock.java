package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyService;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import model.game.projectile.Projectile;
import model.game.tower.DefaultTower;
import model.game.tower.TowerService;
import utils.Vector;
import utils.VectorF;

import java.util.ArrayList;
import java.util.List;

public class Mock {
    public static Enemy createMockEnemy(VectorF pos) {
        return new Enemy() {
            @Override
            public EnemyService getEnemyService() {
                return null;
            }

            @Override
            public void update() {
            }

            @Override
            public VectorF getPos() {
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

    public static DefaultTower createMockTower(Enemy enemy, Vector pos) {
        return new DefaultTower(new TowerService() {
            @Override
            public List<? extends Enemy> getEnemies() {
                ArrayList<Enemy> enemies = new ArrayList<>();
                enemies.add(enemy);
                return enemies;
            }
        }, pos);
    }


    public static Projectile createStationaryProjectile(VectorF pos) {
        return new Projectile() {
            @Override
            public VectorF getPosition() {
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
