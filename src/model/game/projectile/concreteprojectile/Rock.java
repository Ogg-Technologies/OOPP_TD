package model.game.projectile.concreteprojectile;

import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.projectile.ProjectileService;
import utils.VectorD;

public class Rock extends AbstractProjectile {

    private final int damage;

    public Rock(ProjectileService service, VectorD position, VectorD velocity, int damage) {
        super(service, position, velocity);
        this.damage = damage;
    }

    @Override
    protected void onEnemyHit(Enemy enemy) {
        enemy.damage(damage);
        consumed = true;
    }
}
