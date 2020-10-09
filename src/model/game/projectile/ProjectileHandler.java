package model.game.projectile;

import model.game.tower.towerutils.ProjectileCreator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Oskar, Erik
 * <p>
 * Handles everything to do with projectiles
 * Exists to unload Game class
 */
public class ProjectileHandler implements ProjectileCreator {
    private final Collection<Projectile> projectiles = new ArrayList<>();
    private final ProjectileFactory projectileFactory;

    public ProjectileHandler(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

    public void update() {
        synchronized (projectiles) {
            for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
                Projectile p = iterator.next();
                p.update();
                if (p.isConsumed()) {
                    iterator.remove();
                }
            }
        }
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
