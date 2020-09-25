package model.game.projectile;

import model.event.EventSender;
import model.game.projectile.concreteprojectile.Rock;
import utils.VectorD;

public final class ProjectileFactory {

    private final ProjectileService service;
    private final EventSender eventSender;

    public ProjectileFactory(ProjectileService service, EventSender eventSender) {
        this.service = service;
        this.eventSender = eventSender;
    }

    public Projectile createRock(VectorD position, VectorD velocity, int damage) {
        return new Rock(service, position, velocity, damage, this.eventSender);
    }
}
