package model.game.projectile.concreteprojectile;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import utils.Vector;

/**
 * @author Oskar, Samuel, Erik
 * Projectile used by GrizzlyBear
 */
public class Rock extends AbstractProjectile {

    private final int damage;
    private final EventSender eventSender;

    public Rock(Vector position, Vector velocity, int damage, EventSender eventSender) {
        super(position, velocity);
        this.damage = damage;
        this.eventSender = eventSender;
    }

    @Override
    public void onEnemyHit(Enemy enemy) {
        enemy.damage(damage);
        eventSender.sendEvent(new Event(Event.Type.PROJECTILE_HIT, getClass(), this.getPosition()));
        consumed = true;
    }
}
