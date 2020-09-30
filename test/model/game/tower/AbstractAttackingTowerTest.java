package model.game.tower;

import model.game.Mock;
import model.game.enemy.Enemy;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Vector;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAttackingTowerTest {

    private AbstractAttackingTower mockTower;
    private boolean attackMethodHasBeenCalled;

    @BeforeEach
    void setup() {
        attackMethodHasBeenCalled = false;
    }

    private void createMockAbstractAttackingTower(double range) {
        mockTower = new AbstractAttackingTower(new Vector(3, 3), range,
                new ConstantChargeStrategy(1), Mock.createMockEnemyTargeter()) {
            @Override
            protected void attack(Enemy e) {
                attackMethodHasBeenCalled = true;
            }

            @Override
            public void accept(TowerVisitor visitor) { }
        };
    }


    @Test
    void attackMethodCalledWhenEnemyIsWithinRange() {
        createMockAbstractAttackingTower(5);
        assertFalse(attackMethodHasBeenCalled);
        mockTower.update();
        assertTrue(attackMethodHasBeenCalled);
    }

    @Test
    void attackMethodNotCalledWhenEnemyIsNotWithinRange() {
        createMockAbstractAttackingTower(2);
        assertFalse(attackMethodHasBeenCalled);
        mockTower.update();
        assertFalse(attackMethodHasBeenCalled);
    }

    @Test
    void angleIsCorrectAfterAttack() {
        createMockAbstractAttackingTower(5);
        assertEquals(AbstractAttackingTower.DEFAULT_AIM.getAngle(), mockTower.getAngle());
        mockTower.update();
        assertEquals(new Vector(-1, -1).getAngle(), mockTower.getAngle());
    }
}