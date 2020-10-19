package model.game.enemy;

import config.Config;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Oskar, Behroz, Samuel, Erik
 * A Factory pattern for creating enemies.
 * Used by EnemyHandler
 */
public class EnemyFactory {
    private final BaseDamager baseDamager;
    private final List<? extends Vector> path;

    public EnemyFactory(BaseDamager baseDamager, List<? extends Vector> path) {
        this.baseDamager = baseDamager;
        this.path = path;
    }

    public Enemy createFishstick() {
        return new BasicEnemy.Fishstick(baseDamager, new PathIterator(path));
    }

    public Enemy createSwordfish() {
        return new BasicEnemy.Swordfish(baseDamager, new PathIterator(path));
    }

    public Enemy createFishAndChips() {
        return new BasicEnemy.FishAndChips(baseDamager, new PathIterator(path));
    }

    public Enemy createFishInABoat() {
        return new BasicEnemy.FishInABoat(baseDamager, new PathIterator(path));
    }

    public Enemy createSailfish() {
        return new BasicEnemy.Sailfish(baseDamager, new PathIterator(path));
    }

    public Enemy createShark() {
        return new BasicEnemy.Shark(baseDamager, new PathIterator(path));
    }

    public Enemy createFishInFishTank() {
        return new BasicEnemy.FishInAFishTank(baseDamager, new PathIterator(path));
    }

    public Enemy createFlyingFish() {
        return new FlyingFish(baseDamager, new PathIterator(getFlyingPath(this.path, Config.INSTANCE.FLYING_FISH.FLY_AMOUNT)));
    }


    /**
     * Filters the given basePath to only take the nth element from it (flyAmount) and returns it as a new list.
     * Used to make path iterators for flying fish where they skip over most tiles
     *
     * @param basePath  The path containing all the path tile positions
     * @param flyAmount The number of tiles the new path will "fly over" between each element in the path
     * @return A new list containing only the flyAmount'th element from the basePath
     */
    private List<? extends Vector> getFlyingPath(List<? extends Vector> basePath, int flyAmount) {
        List<Vector> flyPath = new ArrayList<>(basePath);
        return IntStream.range(0, flyPath.size())
                .filter(n -> n % flyAmount == 0)
                .mapToObj(flyPath::get)
                .collect(Collectors.toList());
    }
}
