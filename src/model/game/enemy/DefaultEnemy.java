package model.game.enemy;

import utils.Vector;
import utils.VectorF;

public class DefaultEnemy implements Enemy {
    private final EnemyService enemyService;
    private final float SPEED = 0.015f;
    private VectorF pos;
    private Vector targetPos;

    public DefaultEnemy(EnemyService service, VectorF pos) {
        enemyService = service;
        targetPos = enemyService.getFirstTargetPosition();
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


    private void move() {
        float moveDistance = SPEED;
        VectorF targetDelta = targetPos.minus(pos);
        float targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos.asVectorF();
            moveDistance -= targetDistance;
            targetPos = enemyService.getNextTargetPosition(targetPos);
            if (targetPos == null) targetPos = enemyService.getFirstTargetPosition();
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
}
