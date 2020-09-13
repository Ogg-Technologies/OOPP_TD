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
        System.out.println(health);
    }


    private void move() {
        float moveDistance = SPEED;
        VectorF targetDelta = targetPos.minus(pos);
        float targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos.asVectorF();
            moveDistance -= targetDistance;
            Vector nextTargetPosition = enemyService.getNextTargetPosition(targetPos);
            if (nextTargetPosition == null) {
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
    }

    @Override
    public void applyStatusEffect(StatusEffect effect) {

    }

    @Override
    public Health getHealth() {
        return health;
    }
}
