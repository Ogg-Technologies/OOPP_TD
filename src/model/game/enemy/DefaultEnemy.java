package model.game.enemy;

import model.game.Health;
import model.game.MutableHealth;
import utils.Vector;
import utils.VectorF;

public class DefaultEnemy implements Enemy {
    private final EnemyService enemyService;
    private final float SPEED = 0.115f;
    private VectorF pos;
    private Vector targetPos;
    private final MutableHealth health;

    public DefaultEnemy(EnemyService service, VectorF pos) {
        enemyService = service;
        targetPos = enemyService.getFirstTargetPosition();
        health = new MutableHealth(20);
        this.pos = pos;
    }

    @Override
    public EnemyService getEnemyService() {
        return enemyService;
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
        float moveDistance = SPEED;
        VectorF targetDelta = targetPos.minus(pos);
        float targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos.asVectorF();
            moveDistance -= targetDistance;
            Vector nextTargetPosition = enemyService.getNextTargetPosition(targetPos);
            if (nextTargetPosition == null) {
                enemyService.damageBase(health.getCurrent());
                health.setZero();
                return;
            }
            targetPos = nextTargetPosition;
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
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
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
