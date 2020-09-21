package model.game;

import model.game.enemy.Enemy;
import model.game.projectile.Projectile;
import utils.VectorD;

public final class CollisionDetector {
    private static final double ENEMY_RADIUS = 0.5f;

    public static boolean isEnemyAndProjectileColliding(Enemy enemy, Projectile projectile) {
        return isPointInsideCircle(enemy.getPos(), ENEMY_RADIUS, projectile.getPosition());
    }

    private static boolean isPointInsideCircle(VectorD circlePos, double radius, VectorD pointPos) {
        VectorD delta = circlePos.minus(pointPos);
        return delta.getDist() < radius;
    }
}
