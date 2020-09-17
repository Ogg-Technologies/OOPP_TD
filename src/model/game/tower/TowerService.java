package model.game.tower;

import model.game.enemy.Enemy;
import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;

import java.util.List;


public interface TowerService {
    public List<? extends Enemy> getEnemies();

    void addProjectile(Projectile projectile);

    ProjectileFactory getProjectileFactory();

}
