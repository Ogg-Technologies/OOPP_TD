package model.game.projectile;

import model.game.projectile.concreteprojectile.Rock;
import utils.VectorD;

public final class ProjectileFactory {

    private final ProjectileService service;

    public ProjectileFactory(ProjectileService service) {
        this.service = service;
    }

    public Projectile createRock(VectorD position, VectorD velocity, int damage) {
        return new Rock(service, position, velocity, damage);
    }
}
