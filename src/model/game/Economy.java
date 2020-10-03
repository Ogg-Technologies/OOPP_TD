package model.game;


import model.game.enemy.Enemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.tower.Tower;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.MageBear;
import model.game.tower.concretetowers.SniperBear;

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

    private final Map<Class<? extends Tower>, Integer> towerMap = new HashMap<>();
    private final Map<Class<? extends Enemy>, Integer> enemyMap = new HashMap<>();
    private int money = 0;

    /**
     * Creates an instance of the economy class, while also
     * sets up hashMaps that hold the values of the objects.
     */
    public Economy(){
        setupTowerPriceMap();
        setupBasicEnemyMap();
    }

    public Economy(int startMoney){
        this();
        money = startMoney;
    }

    private void setupBasicEnemyMap() {
        enemyMap.put(BasicEnemy.Fishstick.class, 1);
        enemyMap.put(BasicEnemy.Swordfish.class, 2);
        enemyMap.put(BasicEnemy.FishAndChips.class, 4);
        enemyMap.put(BasicEnemy.FishInABoat.class, 8);
        enemyMap.put(BasicEnemy.Sailfish.class, 16);
        enemyMap.put(BasicEnemy.Shark.class, 32);
        enemyMap.put(BasicEnemy.FishInAFishTank.class, 64);
    }

    private void setupTowerPriceMap() {
        towerMap.put(GrizzlyBear.class, 100);
        towerMap.put(MageBear.class, 68);
        towerMap.put(SniperBear.class, 40);
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
    public void addMoney(int money) { //TODO: use this method to add money at end of turn, or create a new method for it.
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
        Integer price = towerMap.get(towerToBuy);
        if(towerMap.get(towerToBuy) == null){
            throw new IllegalArgumentException("This tower does not exists in economy class");
        }
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
        Integer value = enemyMap.get(type);
        if(value == null){
            throw new IllegalArgumentException("This basic enemy type is not in economy class");
        }
        money += value;
    }
    
    /**
     * Gets the price of desired tower.
     * @param towerClass desired tower.
     * @return returns the price.
     */
    public int getTowerPrice(Class<? extends Tower> towerClass) {
        return towerMap.get(towerClass);
    }
}
