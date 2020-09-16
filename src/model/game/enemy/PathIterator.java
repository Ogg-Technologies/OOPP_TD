package model.game.enemy;

import utils.Vector;

import java.util.List;
import java.util.NoSuchElementException;

public class PathIterator {

    private final List<? extends Vector> path;
    private int current;

    public PathIterator(List<? extends Vector> path) {
        this.path = path;
        current = 0;
    }

    public boolean hasNext() {
        return current < path.size();
    }

    public Vector next() {
        if (current + 1 > path.size()) {
            throw new NoSuchElementException("Next element does not exist");
        }
        return path.get(current++);
    }

    public boolean hasPrevious() {
        return current > 0;
    }

    public Vector previous() {
        if (current - 1 < 0) {
            throw new NoSuchElementException("Previous element does not exist");
        }
        return path.get(current--);
    }
}
