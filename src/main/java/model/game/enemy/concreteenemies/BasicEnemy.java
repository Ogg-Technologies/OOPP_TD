package model.game.enemy.concreteenemies;

import config.Config;
import model.game.enemy.AbstractEnemy;
import model.game.enemy.BaseDamager;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.PathIterator;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * A class for all enemies which only differ in health and speed.
 */
public abstract class BasicEnemy extends AbstractEnemy {

    protected BasicEnemy(BaseDamager baseDamager, PathIterator pathIterator, int health, double speed) {
        super(baseDamager, pathIterator, health, speed);
    }

    /**
     * The weakest enemy
     */
    public static class Fishstick extends BasicEnemy {

        public Fishstick(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.Fishstick.HEALTH, Config.Fishstick.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Slightly stronger than the weakest enemy
     */
    public static class Swordfish extends BasicEnemy {

        public Swordfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.Swordfish.HEALTH, Config.Swordfish.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Early-game progression enemy
     */
    public static class FishAndChips extends BasicEnemy {

        public FishAndChips(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.FishAndChips.HEALTH, Config.FishAndChips.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Average speed and health
     */
    public static class FishInABoat extends BasicEnemy {

        public FishInABoat(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.FishInABoat.HEALTH, Config.FishInABoat.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * The fastest fish in the world, but weak
     */
    public static class Sailfish extends BasicEnemy {

        public Sailfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.Sailfish.HEALTH, Config.Sailfish.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Strong enemy, decent speed and pretty high health
     */
    public static class Shark extends BasicEnemy {

        public Shark(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.Shark.HEALTH, Config.Shark.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Tank-like enemy meaning slow and high health
     */
    public static class FishInAFishTank extends BasicEnemy {

        public FishInAFishTank(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.FishInAFishTank.HEALTH, Config.FishInAFishTank.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * Very slow Boss-like enemy with many health
     */
    public static class TankSinatra extends BasicEnemy {

        public TankSinatra(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.TankSinatra.HEALTH, Config.TankSinatra.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }
}
