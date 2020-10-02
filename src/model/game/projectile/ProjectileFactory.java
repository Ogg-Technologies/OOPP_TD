package model.game.projectile;

import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import utils.Vector;

/**
 * @author Oskar, Behroz, Samuel, Erik
 * Factory for creating Projectiles. Used by Game.
 */
public final class ProjectileFactory {

    private final ProjectileService service;
    private final EventSender eventSender;

    public ProjectileFactory(ProjectileService service, EventSender eventSender) {
        this.service = service;
        this.eventSender = eventSender;
    }

    public Projectile createRock(Vector position, Vector target, int damage) {
        return new Rock(service, position, target.minus(position).setMagnitude(0.3), damage, this.eventSender);
    }

    public Projectile createExplodingCharm(Vector position, Enemy target, int damage) {
        return new BombardaCharm(service, position, target, damage, eventSender);
    }
}
