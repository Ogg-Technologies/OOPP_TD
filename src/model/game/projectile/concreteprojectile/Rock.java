package model.game.projectile.concreteprojectile;

import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.projectile.ProjectileService;
import utils.VectorF;

public class Rock extends AbstractProjectile {

    private final int damage;

    public Rock(ProjectileService service, VectorF position, VectorF velocity, int damage) {
        super(service, position, velocity);
        this.damage = damage;
    }

    @Override
    protected void onEnemyHit(Enemy enemy) {
        enemy.damage(damage);
    }
}
