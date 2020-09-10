package model.game.enemy;

import utils.VectorF;

public class DefaultEnemy implements Enemy {
    private final EnemyService enemyService;
    private float speed = 0.005f;
    private VectorF pos;
    private VectorF targetPos = getNextTargetPosition();

    public DefaultEnemy(EnemyService service, VectorF pos) {
        enemyService = service;
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
        float moveDistance = speed;
        VectorF targetDelta = targetPos.minus(pos);
        float targetDistance = targetDelta.getDist();
        while (targetDistance < moveDistance) {
            pos = targetPos;
            moveDistance -= targetDistance;
            targetPos = getNextTargetPosition();
            targetDelta = targetPos.minus(pos);
            targetDistance = targetDelta.getDist();
        }
        VectorF velocity = targetDelta.setMagnitude(moveDistance);
        pos = pos.plus(velocity);
    }

    private VectorF getNextTargetPosition() {
        int x = (int) (Math.random() * 8);
        int y = (int) (Math.random() * 8);
        return new VectorF(x, y);
    }

    @Override
    public VectorF getPos() {
        return pos;
    }
}
