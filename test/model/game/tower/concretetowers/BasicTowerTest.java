package model.game.tower.concretetowers;

import model.game.enemy.Enemy;
import model.game.tower.DefaultTower;
import org.junit.jupiter.api.Test;
import utils.Vector;
import utils.VectorD;

import static model.game.Mock.createMockEnemy;
import static model.game.Mock.createMockTower;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicTowerTest {
    @Test
    void checkEnemyInRange() {
        Enemy enemy = createMockEnemy(new VectorD(20, 1));
        DefaultTower tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(0, tower.getEnemiesInRange(3).size());

        enemy = createMockEnemy(new VectorD(0, 1));
        tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(1, tower.getEnemiesInRange(3).size());
    }


}