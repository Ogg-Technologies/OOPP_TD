package model.game.enemy;

import utils.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Oskar, Erik
 * PathIterator is an Iterator of Vectors which lets you go forwards and backwards in the list of Vectors
 * Used by enemies
 */
public class PathIterator {

    private final List<? extends Vector> path;
    private int current;

    /**
     * Creates a PathIterator from the given path
     *
     * @param path List of Vectors of the whole path
     */
    public PathIterator(List<? extends Vector> path) {
        this.path = path;
        current = -1;
    }

    /**
     * Creates a PathIterator where only the keepNth'th elements are kept from the path argument
     * Used to be able to skip over parts of the path (like flying enemies)
     *
     * @param path    List of Vectors of the whole path
     * @param keepNth Integer determining which nth elements are kept, while the other elements are ignored. Must be > 0
     */
    public PathIterator(List<? extends Vector> path, int keepNth) {
        if (keepNth <= 0) {
            throw new IllegalArgumentException("keepNth cannot be zero or below");
        }

        this.path = calculateFlyingPath(path, keepNth);
        current = -1;
    }

    /**
     * Filters the given basePath to only take the nth element from it (flyAmount) and returns it as a new list.
     * Used to make path iterators for flying fish where they skip over most tiles
     *
     * @param basePath  The path containing all the path tile positions
     * @param flyAmount The number of tiles the new path will "fly over" between each element in the path
     * @return A new list containing only the flyAmount'th element from the basePath
     */
    private List<? extends Vector> calculateFlyingPath(List<? extends Vector> basePath, int flyAmount) {
        List<Vector> flyPath = new ArrayList<>(basePath);
        return IntStream.range(0, flyPath.size())
                .filter(n -> n % flyAmount == 0)
                .mapToObj(flyPath::get)
                .collect(Collectors.toList());
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
