package model.game.enemy;

import utils.Vector;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Oskar, Erik
 * PathIterator is an Iterator of Vectors which lets you go forwards and backwards in the list of Vectors
 * Used by enemies
 */
public class PathIterator {

    private final List<? extends Vector> path;
    private int current;

    public PathIterator(List<? extends Vector> path) {
        this.path = path;
        current = -1;
    }

    public boolean hasNext() {
        return current < path.size() - 1;
    }

    public Vector next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element does not exist");
        }
        return path.get(++current);
    }

    public boolean hasPrevious() {
        return current > 0;
    }

    public Vector previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("Previous element does not exist");
        }
        return path.get(--current);
    }
}
