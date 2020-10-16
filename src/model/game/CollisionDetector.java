package model.game;

import model.game.enemy.Enemy;
import model.game.projectile.Projectile;
import utils.Vector;

/**
 * @author Oskar, Erik
 * Util class with static methods for calculating collisions.
 */
public final class CollisionDetector {
    private static final double ENEMY_RADIUS = 0.5;

    public static boolean isEnemyAndProjectileColliding(Enemy enemy, Projectile projectile) {
        return isPointInsideCircle(enemy.getPos(), ENEMY_RADIUS, projectile.getPosition());
    }

    private static boolean isPointInsideCircle(Vector circlePos, double radius, Vector pointPos) {
        Vector delta = circlePos.minus(pointPos);
        return delta.getDist() < radius;
    }

    public static boolean pointIsInsideRectangle(Vector rectPos, Vector rectSize, Vector pointPos) {
        Vector oppositeCorner = rectPos.plus(rectSize);
        if (pointPos.x > rectPos.x && pointPos.x < oppositeCorner.x) {
            if (pointPos.y > rectPos.y && pointPos.y < oppositeCorner.y) {
                return true;
            }
        }
        return false;
    }
}
