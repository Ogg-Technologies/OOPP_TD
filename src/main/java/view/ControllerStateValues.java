package view;

import model.game.tower.Tower;

/**
 * @author Sebastian, Samuel
 * Getters for some state values of a controller.
 * Used in view components.
 */
public interface ControllerStateValues {
    /**
     * Getter for selected towerType.
     *
     * @return the selected towerType.
     */
    Class<? extends Tower> getSelectedTowerType();

    /**
     * A getter for range of selected tower saved in ControllerState
     *
     * @return the range
     */
    double getSelectedTowerRange();

    /**
     * A getter for every implemented towerProxyType in ControllerState
     * @return an array of all towerProxyTypes
     */
    Class<? extends Tower>[] getAllTowerTypes();

    /**
     * The price of a type of tower
     * @param towerType the type of tower
     * @return the price of specified towerType
     */
    int getTowerPrice(Class<? extends Tower> towerType);

    /**
     * Changes startIndex that is used to know which tower types are currently displayed in towerPanel
     * @param term how much the startIndex will increase/decrease
     */
    void changeStartIndex(int term);

    /**
     * StartIndex is used to know which tower types are currently displayed in towerPanel
     * @return the startIndex
     */
    int getTowerPanelStartIndex();

    /**
     * @return Amount of towers there is implemented
     */
    int getTotalAmountOfTowers();
}
