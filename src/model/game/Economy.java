package model.game;


import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.tower.Tower;
import model.game.tower.concretetowers.GrizzlyBear;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that take care of all the prices and values of towers and enemies.
 * It also stores the amount of money the player has, and will tell if the player can
 * afford different towers.
 */
public class Economy {

    private final Map<Class<? extends Tower>, Integer> towerMap = new HashMap<>();
    private final Map<BasicEnemy.Type, Integer> basicEnemyMap = new HashMap<>();
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
        basicEnemyMap.put(BasicEnemy.Type.FISH_AND_CHIPS, 1);
        basicEnemyMap.put(BasicEnemy.Type.FISH_IN_A_BOAT, 2);
        basicEnemyMap.put(BasicEnemy.Type.FISHSTICK, 1);
        basicEnemyMap.put(BasicEnemy.Type.FISH_IN_FISH_TANK, 10);
        basicEnemyMap.put(BasicEnemy.Type.SAILFISH, 5);
        basicEnemyMap.put(BasicEnemy.Type.SHARK, 5);
        basicEnemyMap.put(BasicEnemy.Type.SWORDFISH, 3);
    }

    private void setupTowerPriceMap() {
        towerMap.put(GrizzlyBear.class, 100);
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
     * @param type a BasicEnemy.Type, which value adds to the players money,
     */
    public void addMoney(BasicEnemy.Type type) { //TODO: use this method to add money when basic enemies dies.
        Integer value = basicEnemyMap.get(type);
        if(value == null){
            throw new IllegalArgumentException("This basic enemy type is not in economy class");
        }
        money += value;
    }
}
