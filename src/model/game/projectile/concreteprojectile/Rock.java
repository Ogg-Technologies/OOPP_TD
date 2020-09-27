package model.game.projectile.concreteprojectile;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.projectile.ProjectileService;
import utils.VectorD;

public class Rock extends AbstractProjectile {

    private final int damage;
    private final EventSender eventSender;

    public Rock(ProjectileService service, VectorD position, VectorD velocity, int damage, EventSender eventSender) {
        super(service, position, velocity);
        this.damage = damage;
        this.eventSender = eventSender;
    }

    @Override
    protected void onEnemyHit(Enemy enemy) {
        enemy.damage(damage);
        eventSender.sendEvent(new Event(Event.Type.PROJECTILE_HIT, getClass(), this.getPosition()));
        consumed = true;
    }
}
