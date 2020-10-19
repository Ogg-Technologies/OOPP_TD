package model.game;


import config.Config;
import model.game.enemy.Enemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;

import java.util.HashMap;
import java.util.Map;


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
     *
     * @return The map mapping each tower to its cost
     */
    private static Map<Class<? extends Tower>, Integer> setupTowerCostMap() {
        Map<Class<? extends Tower>, Integer> map = new HashMap<>();

        Config config = Config.INSTANCE;
        map.put(GrizzlyBear.class, config.GRIZZLY_BEAR.COST);
        map.put(BearryPotter.class, config.BEARRY_POTTER.COST);
        map.put(SniperBear.class, config.SNIPER_BEAR.COST);
        map.put(SovietBear.class, config.SOVIET_BEAR.COST);
        map.put(Barbearian.class, config.BARBEARIAN.COST);
        map.put(BearGrylls.class, config.BEAR_GRYLLS.COST);
        map.put(Beer.class, config.BEER.COST);
        map.put(RubixCubeBear.class, config.RUBIX_CUBE_BEAR.COST);
        map.put(BazookaBear.class, config.BAZOOKABEAR.COST);

        return map;
    }

    /**
     * Defines the reward for killing each enemy as key/value pairs in a map
     *
     * @return The map mapping each enemy to the reward to get when killed
     */
    private static Map<Class<? extends Enemy>, Integer> setupEnemyDeathRewardMap() {
        Map<Class<? extends Enemy>, Integer> map = new HashMap<>();

        Config config = Config.INSTANCE;
        map.put(BasicEnemy.Fishstick.class, config.FISHSTICK.VALUE);
        map.put(BasicEnemy.Swordfish.class, config.SWORDFISH.VALUE);
        map.put(BasicEnemy.FishAndChips.class, config.FISH_AND_CHIPS.VALUE);
        map.put(BasicEnemy.FishInABoat.class, config.FISH_IN_A_BOAT.VALUE);
        map.put(BasicEnemy.Sailfish.class, config.SAILFISH.VALUE);
        map.put(BasicEnemy.Shark.class, config.SHARK.VALUE);
        map.put(BasicEnemy.FishInAFishTank.class, config.FISH_IN_A_FISH_TANK.VALUE);
        map.put(FlyingFish.class, config.FLYING_FISH.VALUE);

        return map;
    }

    private int money = 0;

    /**
     * Creates an instance of the economy class with money equal to zero
     */
    public Economy() {
    }

    public Economy(int startMoney) {
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
     *
     * @param money amount of money to add.
     */
    public void addMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Cannot add negative amount of money");
        }
        this.money += money;
    }

    /**
     * This takes in a tower class to check if it can be bought.
     * If the tower can be bought, money will reduce by the price of the tower.
     * The method also returns a boolean that says if the tower has been "bought"
     *
     * @param towerToBuy the tower to be bought
     * @return a boolean that says if the tower has been bought.
     */
    public boolean buyTower(Class<? extends Tower> towerToBuy) {
        if (!towerMap.containsKey(towerToBuy)) {
            throw new UnsupportedOperationException("This tower (" + towerToBuy.getSimpleName() + ") has not yet been added in economy class");
        }
        Integer price = towerMap.get(towerToBuy);
        if (money >= price) {
            money -= price;
            return true;
        }
        return false;
    }

    /**
     * This method gives money equal to the predefined value of the parameter.
     *
     * @param type a class of the enemy.
     */
    public void addMoney(Class<?> type) {
        if (type == null) {
            return;
        }
        Integer value = enemyMap.get(type);
        if (value == null) {
            throw new UnsupportedOperationException("This enemy type (" + type.getSimpleName() + ") has not yet been added in Economy class");
        }
        money += value;
    }
}
