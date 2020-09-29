package model.game.enemy.concreteenemies;

import model.game.enemy.AbstractEnemy;
import model.game.enemy.BaseDamager;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.PathIterator;

public abstract class BasicEnemy extends AbstractEnemy {
    public static final int BASE_HEALTH = 20;
    public static final double BASE_SPEED = 0.01;

    private BasicEnemy(BaseDamager baseDamager, PathIterator pathIterator, double healthMultiplier, double speedMultiplier) {
        super(baseDamager, pathIterator, (int) (BASE_HEALTH * healthMultiplier), BASE_SPEED * speedMultiplier);
    }

    public static class Fishstick extends BasicEnemy {

        public Fishstick(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 1, 1);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }
    public static class Swordfish extends BasicEnemy {

        public Swordfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 1.5, 1.1);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishAndChips extends BasicEnemy {

        public FishAndChips(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 2, 1.2);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishInABoat extends BasicEnemy {

        public FishInABoat(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 3, 1.3);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Sailfish extends BasicEnemy {

        public Sailfish(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 1, 3.5);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Shark extends BasicEnemy {

        public Shark(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 5, 1.7);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FishInAFishTank extends BasicEnemy {

        public FishInAFishTank(BaseDamager baseDamager, PathIterator pathIterator) {
            super(baseDamager, pathIterator, 10, 0.9);
        }

        @Override
        public void accept(EnemyVisitor visitor) {
            visitor.visit(this);
        }
    }
}
