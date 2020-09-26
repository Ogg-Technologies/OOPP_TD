package model.game.tower.towerutils;

import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;


public interface ProjectileCreator {
    void addProjectile(Projectile projectile);

    ProjectileFactory getProjectileFactory();
}
