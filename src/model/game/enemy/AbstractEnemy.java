package model.game.enemy;

import model.game.Health;
import model.game.MutableHealth;
import utils.Vector;
import utils.VectorD;

public abstract class AbstractEnemy implements Enemy {
    private final BaseDamager baseDamager;
    private final PathIterator pathIterator;
    private VectorD pos;
    private Vector targetPos;
    protected final MutableHealth health;
    private final double speed;

    public AbstractEnemy(BaseDamager baseDamager, PathIterator pathIterator, int maxHealth, double speed) {
        this.baseDamager = baseDamager;
        this.pathIterator = pathIterator;
        health = new MutableHealth(maxHealth);
        pos = pathIterator.next().asVectorD();
        targetPos = pathIterator.next();
        this.speed = speed;
    }

    @Override
    public void update() {
        move();
    }


    /**
     * Moves the enemy a number of tiles equal to its speed along the path defined by enemyService. When a target along
     * the path is reached it will automatically fetch the next one with enemyService.getNextTargetPosition()
     */
    private void move() {
        double moveDistance = speed;
        VectorD targetDelta = targetPos.minus(pos);
        double targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos.asVectorD();
            moveDistance -= targetDistance;
            if (!pathIterator.hasNext()) {
                baseDamager.damageBase(health.getCurrent());
                health.setZero();
                return;
            }
            targetPos = pathIterator.next();
            targetDelta = targetPos.minus(pos);
            targetDistance = targetDelta.getDist();
        }
        VectorD velocity = targetDelta.setMagnitude(moveDistance);
        pos = pos.plus(velocity);
    }

    @Override
    public VectorD getPos() {
        return pos;
    }

    @Override
    public void damage(int amount) {
        health.damage(amount);
    }

    @Override
    public void applyStatusEffect(StatusEffect effect) {

    }

    @Override
    public Health getHealth() {
        return health;
    }
}
