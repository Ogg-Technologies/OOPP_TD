package model.game.tower.towerutils;

import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;

/**
 * Interface used by towers for them to be able to create projectile attacks
 */
public interface ProjectileCreator {

    /**
     * @param projectile Adds the projectile instance to wherever projectiles are stored and updated
     */
    void addProjectile(Projectile projectile);

    /**
     * @return A ProjectileFactory to make towers able to create instances of projectiles
     */
    ProjectileFactory getProjectileFactory();
}
