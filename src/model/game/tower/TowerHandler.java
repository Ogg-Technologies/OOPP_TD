package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.SniperBear;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.ProjectileCreator;
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

    /**
     * Creates a grizzly tower at position v
     *
     * @param v, a {@code Vector} that describes position
     */
    public void createGrizzlyBear(Vector v) {
        towers.add(factory.createGrizzlyBear(v));
    }

    /**
     * Creates a mage bear tower at position v
     *
     * @param v, a {@code Vector} that describes position
     */
    public void createMageBear(Vector v) {
        // TODO: Should probably not have methods for each tower, just give Game a reference to TowerFactory
        towers.add(factory.createMageBear(v));
    }

    /**
     * Creates a sniper tower at position v
     *
     * @param v, a {@code Vector} that describes position
     */
    public void createSniperBear(Vector v) {
        towers.add(factory.createSniperBear(v));
    }

    /**
     * Creates a tower based on the tower class
     * @param pos of tower
     * @param towerClass the class that represent the concrete tower
     */
    public void createTower(Vector pos, Class<? extends Tower> towerClass) {
        towers.add(factory.createTower(pos, towerClass));
    }

    /**
     * Get start range of specified tower
     * @param towerClass the specified tower
     * @return the range
     */
    public double getRangeOfTower(Class<? extends Tower> towerClass) {
        if(towerClass == GrizzlyBear.class){
            return GrizzlyBear.RANGE;
        }
        if(towerClass == BearryPotter.class){
            return BearryPotter.RANGE;
        }
        if(towerClass == SniperBear.class){
            return SniperBear.RANGE;
        }
        throw new IllegalArgumentException("The towerClass: " + towerClass + " is not recognized");
    }
}
