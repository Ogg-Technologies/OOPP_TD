package model.game.tower;

import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Oskar, Sebastian, Behroz, Erik
 * Handles everything to do with towers
 * Exists to unload Game class
 */
public class TowerHandler {

    private final Collection<Tower> towers;

    public TowerHandler() {
        towers = new ArrayList<>();
    }

    public Collection<? extends Tower> getTowers() {
        return Collections.unmodifiableCollection(new ArrayList<>(towers));
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
        }
    }

    /**
     * Creates tower and adds it to the tower list in this class.
     *
     * @param towerFactory the method that actually creates the tower
     * @param pos          the position of the tower to be created
     */
    public void createTower(AbstractTowerFactory towerFactory, Vector pos) {
        towers.add(towerFactory.createTower(pos));
    }

    /**
     * Checks if there is a tower on a particular tilePos
     *
     * @param tilePos the pos to look at
     * @return true if there is a tower on this pos, else false
     */
    public boolean isTowerAt(Vector tilePos) {
        for (Tower t : towers) {
            if (t.getPos().equals(tilePos)) {
                return true;
            }
        }
        return false;
    }
}
