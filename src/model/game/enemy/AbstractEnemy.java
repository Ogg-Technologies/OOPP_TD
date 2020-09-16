package model.game.enemy;

import model.game.Health;
import model.game.MutableHealth;
import utils.Vector;
import utils.VectorF;

public abstract class AbstractEnemy implements Enemy {
    private final EnemyService enemyService;
    private final PathIterator pathIterator;
    private VectorF pos;
    private Vector targetPos;
    protected final MutableHealth health;
    private final double speed;

    public AbstractEnemy(EnemyService service, PathIterator pathIterator, int maxHealth, double speed) {
        enemyService = service;
        this.pathIterator = pathIterator;
        health = new MutableHealth(maxHealth);
        pos = pathIterator.next().asVectorF();
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
        float moveDistance = (float) speed;
        VectorF targetDelta = targetPos.minus(pos);
        float targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos.asVectorF();
            moveDistance -= targetDistance;
            if (!pathIterator.hasNext()) {
                enemyService.damageBase(health.getCurrent());
                health.setZero();
                return;
            }
            targetPos = pathIterator.next();
            targetDelta = targetPos.minus(pos);
            targetDistance = targetDelta.getDist();
        }
        VectorF velocity = targetDelta.setMagnitude(moveDistance);
        pos = pos.plus(velocity);
    }

    @Override
    public VectorF getPos() {
        return pos;
    }

    @Override
    public void damage(int amount) {
        health.damage(amount);
        System.out.println(health);
    }

    @Override
    public void applyStatusEffect(StatusEffect effect) {

    }

    @Override
    public Health getHealth() {
        return health;
    }
}
