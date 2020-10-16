package model.game.projectile;

import model.game.CollisionDetector;
import model.game.enemy.Enemy;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * @author Oskar, Erik
 * <p>
 * Handles everything to do with projectiles
 * Exists to unload Game class
 */
public class ProjectileHandler implements ProjectileCreator {
    private static final int EDGE_DELTA = 1;
    private final Collection<Projectile> projectiles = new ArrayList<>();
    private final ProjectileFactory projectileFactory;
    private final Vector mapSize;
    private final EnemyGetter enemyGetter;

    public ProjectileHandler(ProjectileFactory projectileFactory, Vector mapSize, EnemyGetter enemyGetter) {
        this.projectileFactory = projectileFactory;
        this.mapSize = mapSize;
        this.enemyGetter = enemyGetter;
    }

    public void update() {
        synchronized (projectiles) {
            for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
                Projectile p = iterator.next();
                p.update();
                if (projectileIsOutsideMap(p)) {
                    iterator.remove();
                    continue;
                }
                for (Enemy e : getEnemiesCollidedWith(p)) {
                    p.onEnemyHit(e);
                    if (p.isConsumed()) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }


    private Collection<? extends Enemy> getEnemiesCollidedWith(Projectile p) {
        return enemyGetter.getEnemies().stream()
                .filter(enemy -> CollisionDetector.isEnemyAndProjectileColliding(enemy, p))
                .collect(Collectors.toList());
    }

    private boolean projectileIsOutsideMap(Projectile p) {
        Vector position = p.getPosition();
        if (position.x < -EDGE_DELTA || position.y < -EDGE_DELTA
                || position.x > mapSize.x + EDGE_DELTA || position.y > mapSize.y + EDGE_DELTA) {
            return true;
        }
        return false;
    }


    @Override
    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    @Override
    public void addProjectile(Projectile projectile) {
        synchronized (projectiles) {
            projectiles.add(projectile);
        }
    }

    public Collection<Projectile> getProjectiles() {
        synchronized (projectiles) {
            return new ArrayList<>(projectiles);
        }
    }
}
