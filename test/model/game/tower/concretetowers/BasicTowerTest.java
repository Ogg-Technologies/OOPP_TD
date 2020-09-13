package model.game.tower.concretetowers;

import model.game.Health;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyService;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import model.game.tower.DefaultTower;
import model.game.tower.TowerService;
import org.junit.jupiter.api.Test;
import utils.Vector;
import utils.VectorF;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicTowerTest {
    @Test
    void checkEnemyInRange() {
        Enemy enemy = createMockEnemy(new VectorF(20, 1));
        DefaultTower tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(0, tower.getEnemiesInRange(3).size());

        enemy = createMockEnemy(new VectorF(0, 1));
        tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(1, tower.getEnemiesInRange(3).size());
    }


    private Enemy createMockEnemy(VectorF pos) {
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

    private DefaultTower createMockTower(Enemy enemy, Vector pos) {
        return new DefaultTower(new TowerService() {
            @Override
            public List<? extends Enemy> getEnemies() {
                ArrayList<Enemy> enemies = new ArrayList<>();
                enemies.add(enemy);
                return enemies;
            }
        }, pos);
    }


}