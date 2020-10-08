package model;

import model.game.tower.AbstractTowerFactory;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import utils.Vector;

/**
 * @author Sebastian, Samuel, Erik
 * An interface for sending user input to the Model from the controller
 */
public interface ModelInputListener {

    /**
     * What happens when model gets a click input from controller
     *
     * @param towerFactory a towerCreator interface that can create towers
     * @param pos          position of the tile that got clicked on
     * @param towerType    the type of tower that should be placed
     */
    void onTileClick(AbstractTowerFactory towerFactory, Vector pos, Class<? extends Tower> towerType);

    /**
     * Gives a call to model/game that the user want a new wave
     */
    void onStartNewWave();

    /**
     * Method for getting a towerFactory to create interfaces of type AbstractTowerFactory
     * @return the towerFactory
     */
    TowerFactory getFactory();
}
