package model.game.tower.towerutils;

import model.game.tower.Tower;

import java.util.Collection;

/**
 * @author Oskar
 * Functional interface which provides a collection of all towers on the map used by TowerFinder
 */
@FunctionalInterface
public interface TowerGetter {
    Collection<? extends Tower> getTowers();
}
