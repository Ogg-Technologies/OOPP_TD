package model.game.enemy.concreteenemies;

import application.Constant;
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

    private BasicEnemy(BaseDamager baseDamager, PathIterator pathIterator, double healthMultiplier, double speedMultiplier) {
        super(baseDamager, pathIterator, (int) (BASE_HEALTH * healthMultiplier), BASE_SPEED * speedMultiplier);
    }

    public static class Fishstick extends BasicEnemy {

        public Fishstick(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.FISHSTICK, Constant.getInstance().ENEMY_SPEED.FISHSTICK);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Swordfish extends BasicEnemy {

        public Swordfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.SWORDFISH, Constant.getInstance().ENEMY_SPEED.SWORDFISH);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishAndChips extends BasicEnemy {

        public FishAndChips(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.FISH_AND_CHIPS, Constant.getInstance().ENEMY_SPEED.FISH_AND_CHIPS);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishInABoat extends BasicEnemy {

        public FishInABoat(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.FISH_IN_A_BOAT, Constant.getInstance().ENEMY_SPEED.FISH_IN_A_BOAT);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Sailfish extends BasicEnemy {

        public Sailfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.SAILFISH, Constant.getInstance().ENEMY_SPEED.SAILFISH);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Shark extends BasicEnemy {

        public Shark(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.SHARK, Constant.getInstance().ENEMY_SPEED.SHARK);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishInAFishTank extends BasicEnemy {

        public FishInAFishTank(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, Constant.getInstance().ENEMY_HEALTH.FISH_IN_A_FISH_TANK, Constant.getInstance().ENEMY_SPEED.FISH_IN_A_FISH_TANK);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }
}
