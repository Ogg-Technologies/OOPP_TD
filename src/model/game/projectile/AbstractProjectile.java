package model.game.projectile;

import model.game.CollisionDetector;
import model.game.enemy.Enemy;
import utils.Vector;

public abstract class AbstractProjectile implements Projectile {

    private static final int EDGE_DELTA = 1;

    protected final ProjectileService service;
    protected Vector velocity;
    private Vector position;
    protected boolean consumed;

    public AbstractProjectile(ProjectileService service, Vector position, Vector velocity) {
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
        if (position.x < -EDGE_DELTA || position.y < -EDGE_DELTA
                || position.x > mapSize.x + EDGE_DELTA || position.y > mapSize.y + EDGE_DELTA) {
            consumed = true;
        }
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }
}
