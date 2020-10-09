package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.tower.Tower;
import model.game.tower.concretetowers.GrizzlyBear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sebastian
 */
class EconomyTest {
    Economy economy;

    @BeforeEach
    void setup(){
        economy = new Economy();
    }

    @Test
    void getMoneyFromBeginning(){
        assertEquals(0, economy.getMoney());
    }

    @Test
    void addMoney(){
        economy.addMoney(10);
    }

    @Test
    void addMoneyAndAssertThatTheMoneyIsThatMuch(){
        economy.addMoney(10);
        assertEquals(10, economy.getMoney());
    }

    @Test
    void addMoneyTwice(){
        economy.addMoney(10);
        assertEquals(10, economy.getMoney());
        economy.addMoney(10);
        assertEquals(20, economy.getMoney());
    }

    @Test
    void addNegativeMoney(){
        assertThrows(IllegalArgumentException.class, () -> economy.addMoney(-10));
    }

    @Test
    void canBuyTowerIfHasEnoughMoney(){
        economy.addMoney(100);
        assertTrue(economy.buyTower(GrizzlyBear.class));
    }

    @Test
    void buyingTowerDecreasesAmountOfMoney(){
        int moneyCurrent = 100;
        economy.addMoney(moneyCurrent);
        economy.buyTower(GrizzlyBear.class);
        assertTrue(moneyCurrent > economy.getMoney());
    }

    @Test
    void cannotHaveNegativeMoney(){
        economy.addMoney(1);
        economy.buyTower(GrizzlyBear.class);
        assertTrue(economy.getMoney() >= 0);
    }

    @Test
    void cannotBuyTowersIfMoneyDoesNotDecrease(){
        int lastMoney = economy.getMoney();
        assertFalse(economy.buyTower(GrizzlyBear.class));
        assertEquals(lastMoney, economy.getMoney());
    }

    @Test
    void cannotBuyNotPricedTowers(){
        assertThrows(UnsupportedOperationException.class, () -> economy.buyTower(Tower.class));
    }

    @Test
    void canGetMoreMoneyIfBasicEnemyIsInArgument(){
        int curMoney = economy.getMoney();
        economy.addMoney(BasicEnemy.FishAndChips.class);
        assertTrue(curMoney < economy.getMoney());
    }

    @Test
    void canOnlyTakeInKnownTypesOfEnemy(){
        assertThrows(UnsupportedOperationException.class, () -> economy.addMoney(Enemy.class));
    }

}