package model.game.enemy;

import model.game.health.Health;
import model.game.health.MutableHealth;
import utils.Vector;

/**
 * @author Oskar, Behroz, Erik
 * A subtype of Enemy which implements common functionality for all enemies.
 */
public abstract class AbstractEnemy implements Enemy {
    private final BaseDamager baseDamager;
    private final PathIterator pathIterator;
    private Vector pos;
    private Vector targetPos;
    protected final MutableHealth health;
    private final double speed;

    public AbstractEnemy(BaseDamager baseDamager, PathIterator pathIterator, int maxHealth, double speed) {
        this.baseDamager = baseDamager;
        this.pathIterator = pathIterator;
        health = new MutableHealth(maxHealth);
        pos = pathIterator.next();
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
        Vector targetDelta = targetPos.minus(pos);
        double targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos;
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
        Vector velocity = targetDelta.setMagnitude(moveDistance);
        pos = pos.plus(velocity);
    }

    @Override
    public Vector getPos() {
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
