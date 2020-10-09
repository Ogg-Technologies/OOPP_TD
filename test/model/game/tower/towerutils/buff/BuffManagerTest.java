package model.game.tower.towerutils.buff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuffManagerTest {

    private BuffManager buffManager;

    @BeforeEach
    void setUp() {
        buffManager = new BuffManager();
        buffManager.applyBuff(m -> m.addDamage(30), 1);
        buffManager.applyBuff(m -> m.addRange(30), 1);
        buffManager.applyBuff(m -> m.addFireRate(30), 1);
        buffManager.applyBuff(m -> {
            m.addDamage(30);
            m.addRange(30);
        }, 10);
    }

    @Test
    void canSumAllActiveBuffs() {
        buffManager.update();
        TowerMultipliers mult = buffManager.getTowerMultipliers();
        assertEquals(1.6, mult.getDamageMultiplier(), 0.0001);
        assertEquals(1.6, mult.getRangeMultiplier(), 0.0001);
        assertEquals(1.3, mult.getFireRateMultiplier(), 0.0001);
    }

    @Test
    void removesBuffsWhenExpired() {
        buffManager.update();
        buffManager.update();
        TowerMultipliers mult = buffManager.getTowerMultipliers();
        assertEquals(1.3, mult.getDamageMultiplier(), 0.0001);
        assertEquals(1.3, mult.getRangeMultiplier(), 0.0001);
        assertEquals(1.0, mult.getFireRateMultiplier(), 0.0001);
    }
}