package model.game.projectile.concreteprojectile;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.projectile.ProjectileService;
import utils.Vector;

/**
 * @author Erik
 * BombardaCharm is a heat seeking charm/projectile cast by the MageBear that causes a small explosion
 * Source: https://harrypotter.fandom.com/wiki/Exploding_Charm
 * It was only recently that wizard bears learned to harness the dark arts and make the Bombarda charm seek out their target
 */
public class BombardaCharm extends AbstractProjectile {

    private static final double SPEED = 0.06;

    private final int damage;
    private final EventSender eventSender;
    private Enemy target;

    public BombardaCharm(ProjectileService service, Vector position, Enemy target, int damage, EventSender eventSender) {
        super(service, position, target.getPos().minus(position).setMagnitude(SPEED));
        this.damage = damage;
        this.eventSender = eventSender;
        this.target = target;
    }

    /**
     * Overridden to achieve heat seeking projectile
     * Continuously updates the velocity to aim at the target
     */
    @Override
    public void update() {
        // If the targeted enemy is dead, don't change velocity, keep going straight
        if(target != null) {//TODO temp fix?
            if (target.getHealth().isDead()) {
                target = null;

            } else {
                velocity = target.getPos().minus(getPosition()).setMagnitude(SPEED);
            }
        }
        super.update();
    }

    @Override
    protected void onEnemyHit(Enemy enemy) {   // TODO: Make the explosion part of the description of the projectile happen (?)
        enemy.damage(damage);
        eventSender.sendEvent(new Event(Event.Type.PROJECTILE_HIT, this.getClass(), getPosition()));
        consumed = true;
    }
}
