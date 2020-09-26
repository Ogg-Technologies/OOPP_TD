package model.game.tower;

import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;


public interface ProjectileCreator {
    void addProjectile(Projectile projectile);

    ProjectileFactory getProjectileFactory();
}
