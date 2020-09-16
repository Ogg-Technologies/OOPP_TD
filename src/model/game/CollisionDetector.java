package model.game;

import model.game.enemy.Enemy;
import model.game.projectile.Projectile;
import utils.VectorF;

public final class CollisionDetector {
    private static final float ENEMY_RADIUS = 0.5f;

    public static boolean isEnemyAndProjectileColliding(Enemy enemy, Projectile projectile) {
        return isPointInsideCircle(enemy.getPos(), ENEMY_RADIUS, projectile.getPosition());
    }

    private static boolean isPointInsideCircle(VectorF circlePos, float radius, VectorF pointPos) {
        VectorF delta = circlePos.minus(pointPos);
        return delta.getDist() < radius;
    }
}
