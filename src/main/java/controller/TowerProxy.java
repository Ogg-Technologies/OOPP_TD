package controller;

import model.game.tower.AbstractTowerFactory;
import model.game.tower.Tower;

/**
 * @author Sebastian
 * This class is for mimicing some data from a concrete tower, but not needing to create a concrete tower object.
 * It is used by Controller state
 */
public class TowerProxy {

    private final AbstractTowerFactory towerFactory;
    private final double range;
    private final Class<? extends Tower> towerType;
    private final int price;

    public double getRange() {
        return range;
    }

    public Class<? extends Tower> getTowerType() {
        return towerType;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Constructor is only used to store some data in object
     *
     * @param towerFactory How to create this representation as a concrete tower
     * @param range        the towers range
     * @param towerType    the type of the tower, stored as a Class
     * @param price        the price of the tower
     */
    public TowerProxy(AbstractTowerFactory towerFactory, double range, Class<? extends Tower> towerType, int price) {
        this.towerFactory = towerFactory;
        this.range = range;
        this.towerType = towerType;
        this.price = price;
    }

    public AbstractTowerFactory getTowerFactory() {
        return towerFactory;
    }

    /**
     * This method is for when checking if this proxy is the same type
     *
     * @param otherTowerType the other type to check against
     * @return a boolean that is true if same type, otherwise false
     */
    boolean correctTowerType(Class<? extends Tower> otherTowerType) {
        return otherTowerType == towerType;
    }

}
