package model.game.projectile;

import model.game.CollisionDetector;
import model.game.enemy.Enemy;
import utils.Vector;
import utils.VectorD;

public abstract class AbstractProjectile implements Projectile {

    private static final int EDGE_DELTA = 1;

    protected final ProjectileService service;
    protected VectorD velocity;
    private VectorD position;
    protected boolean consumed;

    public AbstractProjectile(ProjectileService service, VectorD position, VectorD velocity) {
        this.service = service;
        this.position = position;
        this.velocity = velocity;
        consumed = false;
    }

    @Override
    public void update() {
        position = position.plus(velocity);

        removeIfOutsideMap();
        checkCollisions();
    }

    private void checkCollisions() {
        for (Enemy e : service.getEnemies()) {
            if (CollisionDetector.isEnemyAndProjectileColliding(e, this)) {
                onEnemyHit(e);
            }
            if (isConsumed()) {
                return;
            }
        }
    }

    protected abstract void onEnemyHit(Enemy enemy);

    private void removeIfOutsideMap() {
        Vector mapSize = service.getMapSize();
        if (position.getX() < -EDGE_DELTA || position.getY() < -EDGE_DELTA
                || position.getX() > mapSize.getX() + EDGE_DELTA || position.getY() > mapSize.getY() + EDGE_DELTA) {
            consumed = true;
        }
    }

    @Override
    public VectorD getPosition() {
        return position;
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }
}
