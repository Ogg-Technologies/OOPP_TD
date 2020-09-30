package model.game.tower.towerutils.chargestrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstantChargeStrategyTest {

    ConstantChargeStrategy constantChargeStrategy;

    @BeforeEach
    void setup() {
        constantChargeStrategy = new ConstantChargeStrategy(5);
    }

    @Test
    void newStrategyNotReady() {
        assertFalse(constantChargeStrategy.isReadyToFire());
    }

    @Test
    void isReadyWithEnoughUpdates() {
        for (int i = 0; i < 5 - 1; i++) {
            constantChargeStrategy.update();
            assertFalse(constantChargeStrategy.isReadyToFire());
        }
        constantChargeStrategy.update();
        assertTrue(constantChargeStrategy.isReadyToFire());
    }

    @Test
    void didFireResetsCharge() {
        for (int i = 0; i < 5; i++) {
            constantChargeStrategy.update();
        }
        constantChargeStrategy.didFire();
        for (int i = 0; i < 5; i++) {
            assertFalse(constantChargeStrategy.isReadyToFire());
            constantChargeStrategy.update();
        }
        assertTrue(constantChargeStrategy.isReadyToFire());
    }
}