package model.game.tower;

import model.event.EventSender;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Handles everything to do with towers
 * Exists to unload Game class
 */
public class TowerHandler {

    private final TowerFactory factory;
    private final Collection<Tower> towers;

    public TowerHandler(EnemyGetter enemyGetter, ProjectileCreator projectileCreator, EventSender eventSender) {
        factory = new TowerFactory(enemyGetter, projectileCreator, eventSender);
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
     * Checks if there is a tower on a particular tilePos
     * @param tilePos the pos to look at
     * @return true if there is a tower on this pos, else false
     */
    public boolean isTowerAt(Vector tilePos) {
        for(Tower t : towers){
            if(t.getPos().equals(tilePos)){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a grizzly tower at position v
     * @param v, a {@code Vector} that describes position
     */
    public void createGrizzlyBear(Vector v) {
        towers.add(factory.createGrizzlyBear(v));
    }
}
