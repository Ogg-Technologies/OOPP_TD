package model.game.tower.towerutils;

import model.game.tower.Tower;
import utils.Vector;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Oskar
 * Class used by buff towers to get a collection all towers within their range
 */
public class TowerFinder {
    private TowerGetter towerGetter;

    public TowerFinder(TowerGetter towerGetter) {
        this.towerGetter = towerGetter;
    }

    /**
     * Method which finds all towers within a given range of a position
     *
     * @param pos   the center position
     * @param range the radius around the position
     * @return a collection of all towers within that radius
     */
    public Collection<? extends Tower> getTowersInRange(Vector pos, double range) {
        return towerGetter.getTowers()
                .stream()
                .filter(t -> t.getPos().minus(pos).getDist() < range)
                .collect(Collectors.toList());
    }
}
