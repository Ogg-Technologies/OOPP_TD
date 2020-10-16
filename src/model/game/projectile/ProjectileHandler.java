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
    /**
     * The extra distance a Projectile has to be outside the map for it to be removed
     */
    private static final int EDGE_DELTA = 1;
    private final Collection<Projectile> projectiles = new ArrayList<>();
    private final ProjectileFactory projectileFactory;
    private final Vector mapSize;
    private final EnemyGetter enemyGetter;

    /**
     * @param projectileFactory a way of creating projectiles
     * @param mapSize           the size of the map. Must not change during the game
     * @param enemyGetter       a way of getting all enemies currently on the map
     */
    public ProjectileHandler(ProjectileFactory projectileFactory, Vector mapSize, EnemyGetter enemyGetter) {
        this.projectileFactory = projectileFactory;
        this.mapSize = mapSize;
        this.enemyGetter = enemyGetter;
    }

    /**
     * Updates all the projectiles and handles their collisions
     */
    public void update() {
        // Caches getEnemies() since it does a defensive copy every call
        Collection<? extends Enemy> enemies = enemyGetter.getEnemies();
        synchronized (projectiles) {
            for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
                Projectile p = iterator.next();
                p.update();
                if (projectileIsOutsideMap(p)) {
                    iterator.remove();
                    continue;
                }
                for (Enemy e : getEnemiesCollidedWith(enemies, p)) {
                    p.onEnemyHit(e);
                    if (p.isConsumed()) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Gets a collection of all enemies that the given projectile is colliding with
     *
     * @param enemies a collection of all enemies on the map
     * @param p       the projectile currently checked
     * @return a collection of all enemies that collides with the given projectile
     */
    private Collection<? extends Enemy> getEnemiesCollidedWith(Collection<? extends Enemy> enemies, Projectile p) {
        return enemies.stream()
                .filter(enemy -> CollisionDetector.isEnemyAndProjectileColliding(enemy, p))
                .collect(Collectors.toList());
    }

    /**
     * Checks if the projectile is outside the map
     *
     * @param p the projectile to check
     * @return true if the center of the projectile is further than EDGE_DELTA outside the edge of the map, false otherwise
     */
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
