package model.game;


import model.game.enemy.Enemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;

import java.util.HashMap;
import java.util.Map;

import static application.PropertyValues.EnemyValues.*;
import static application.PropertyValues.TowerPrices.*;

/**
 * @author Sebastian, Behroz, Samuel, Erik
 * A class that take care of all the prices and values of towers and enemies.
 * It also stores the amount of money the player has, and will tell if the player can
 * afford different towers.
 * Used by Game to keep track of the economy in the game.
 */
public class Economy {

    private static final Map<Class<? extends Tower>, Integer> towerMap = setupTowerCostMap();
    private static final Map<Class<? extends Enemy>, Integer> enemyMap = setupEnemyDeathRewardMap();

    /**
     * Defines the cost of each tower as key/value pairs in a map
     * @return The map mapping each tower to its cost
     */
    private static Map<Class<? extends Tower>, Integer> setupTowerCostMap() {
        Map<Class<? extends Tower>, Integer> map = new HashMap<>();

        map.put(GrizzlyBear.class, 100);
        map.put(BearryPotter.class, 68);
        map.put(SniperBear.class, 40);
        map.put(SovietBear.class, 80);
        map.put(Barbearian.class, 90);

        return map;
    }

    /**
     * Defines the reward for killing each enemy as key/value pairs in a map
     * @return The map mapping each enemy to the reward to get when killed
     */
    private static Map<Class<? extends Enemy>, Integer> setupEnemyDeathRewardMap() {
        Map<Class<? extends Enemy>, Integer> map = new HashMap<>();

        map.put(BasicEnemy.Fishstick.class, 1);
        map.put(BasicEnemy.Swordfish.class, 2);
        map.put(BasicEnemy.FishAndChips.class, 4);
        map.put(BasicEnemy.FishInABoat.class, 8);
        map.put(BasicEnemy.Sailfish.class, 16);
        map.put(BasicEnemy.Shark.class, 32);
        map.put(BasicEnemy.FishInAFishTank.class, 64);

        return map;
    }

    private int money = 0;

    /**
     * Creates an instance of the economy class with money equal to zero
     */
    public Economy() { }

    public Economy(int startMoney){
        this();
        money = startMoney;
    }

    /**
     * @return amount of money the player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * Adds money to the player
     * @param money amount of money to add.
     */
    public void addMoney(int money) {
        if(money < 0){
            throw new IllegalArgumentException("Cannot add negative amount of money");
        }
        this.money += money;
    }

    /**
     * This takes in a tower class to check if it can be bought.
     * If the tower can be bought, money will reduce by the price of the tower.
     * The method also returns a boolean that says if the tower has been "bought"
     * @param towerToBuy the tower to be bought
     * @return a boolean that says if the tower has been bought.
     */
    public boolean buyTower(Class<? extends Tower> towerToBuy) {
        if (!towerMap.containsKey(towerToBuy)) {
            throw new UnsupportedOperationException("This tower (" + towerToBuy.getSimpleName() + ") has not yet been added in economy class");
        }
        Integer price = towerMap.get(towerToBuy);
        if(money >= price) {
            money -= price;
            return true;
        }
        return false;
    }

    /**
     * This method gives money equal to the predefined value of the parameter.
     * @param type a class of the enemy.
     */
    public void addMoney(Class<?> type) {
        if (type == null) {
            return;
        }
        Integer value = enemyMap.get(type);
        if(value == null){
            throw new UnsupportedOperationException("This enemy type (" + type.getSimpleName() + ") has not yet been added in Economy class");
        }
        money += value;
    }
    
    /**
     * Gets the price of desired tower.
     * @param towerClass desired tower.
     * @return returns the price.
     */
    public int getTowerPrice(Class<? extends Tower> towerClass) {
        if (!towerMap.containsKey(towerClass)) {
            throw new UnsupportedOperationException("This tower (" + towerClass.getSimpleName() + ") has not yet been added in economy class");
        }
        return towerMap.get(towerClass);
    }
}
