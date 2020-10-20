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

    public static final int BASE_HEALTH = 20;
    public static final double BASE_SPEED = 0.01;

    protected BasicEnemy(BaseDamager baseDamager, PathIterator pathIterator, double healthMultiplier, double speedMultiplier) {
        super(baseDamager, pathIterator, (int) (BASE_HEALTH * healthMultiplier), BASE_SPEED * speedMultiplier);
    }

    /**
     * The weakest enemy
     */
    public static class Fishstick extends BasicEnemy {

        public Fishstick(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Config.INSTANCE.FISHSTICK.HEALTH, Config.INSTANCE.FISHSTICK.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.SWORDFISH.HEALTH, Config.INSTANCE.SWORDFISH.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.FISH_AND_CHIPS.HEALTH, Config.INSTANCE.FISH_AND_CHIPS.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.FISH_IN_A_BOAT.HEALTH, Config.INSTANCE.FISH_IN_A_BOAT.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.SAILFISH.HEALTH, Config.INSTANCE.SAILFISH.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.SHARK.HEALTH, Config.INSTANCE.SHARK.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.FISH_IN_A_FISH_TANK.HEALTH, Config.INSTANCE.FISH_IN_A_FISH_TANK.SPEED);
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
            super(baseDamager, pathIterator, Config.INSTANCE.TANK_SINATRA.HEALTH, Config.INSTANCE.TANK_SINATRA.SPEED);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }
}
