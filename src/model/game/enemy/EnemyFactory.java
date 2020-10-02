package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import utils.Vector;

import java.util.List;

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
}
