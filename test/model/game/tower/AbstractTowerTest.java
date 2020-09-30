package model.game.tower;

import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Vector;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTowerTest {

    private AbstractTower mockTower;
    private boolean hasCalledTryFire;

    @BeforeEach
    void setup() {
        hasCalledTryFire = false;
        mockTower = new AbstractTower(new Vector(5, 5), 10, new ConstantChargeStrategy(2)) {
            @Override
            protected boolean tryFire() {
                hasCalledTryFire = true;
                return true;
            }

            @Override
            public void accept(TowerVisitor visitor) { }
        };
    }

    @Test
    void towerRemembersPosition() {
        assertEquals(new Vector(5, 5), mockTower.getPos());
    }

    @Test
    void towerRemembersRange() {
        assertEquals(10, mockTower.getRange());
    }

    @Test
    void towerCallsTryFireWhenChargeStrategyIsDone() {
        assertFalse(hasCalledTryFire);
        mockTower.update(); // Mac charge == 2, 2 updates
        assertFalse(hasCalledTryFire);
        mockTower.update();
        assertTrue(hasCalledTryFire);
    }
}