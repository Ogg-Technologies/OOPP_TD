package model.game.projectile.concreteprojectile;

import config.Config;
import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.tower.towerutils.EnemyTargeter;
import utils.Vector;

/**
 * @author Oskar, Sebastian, Erik
 * BombardaCharm is a heat seeking charm/projectile cast by the BearryPotter that causes a small explosion
 * Source: https://harrypotter.fandom.com/wiki/Exploding_Charm
 * It was only recently that wizard bears learned to harness the dark arts and make the Bombarda charm seek out their target
 */
public class BombardaCharm extends AbstractProjectile {

    private static final double EXPLOSION_RADIUS = Config.BombardaCharm.EXPLOSION_RADIUS;

    private final int damage;
    private final EventSender eventSender;
    private final EnemyTargeter enemyTargeter;
    private Enemy target;

    public BombardaCharm(Vector position, Enemy target, int damage, EventSender eventSender, EnemyTargeter enemyTargeter) {
        super(position, target.getPos().minus(position).setMagnitude(Config.BombardaCharm.SPEED));
        this.damage = damage;
        this.eventSender = eventSender;
        this.target = target;
        this.enemyTargeter = enemyTargeter;
    }

    /**
     * Overridden to achieve heat seeking projectile
     * Continuously updates the velocity to aim at the target
     */
    @Override
    public void update() {
        // If the targeted enemy is dead, don't change velocity, keep going straight
        if (target != null) {
            if (target.getHealth().isDead()) {
                target = null;
            } else {
                velocity = target.getPos().minus(getPosition()).setMagnitude(Config.BombardaCharm.SPEED);
            }
        }
        super.update();
    }

    @Override
    public void onEnemyHit(Enemy enemy) {
        damageEnemiesInRadius();
        eventSender.sendEvent(new Event(Event.Type.PROJECTILE_HIT, this.getClass(), getPosition()));
        consumed = true;
    }

    private void damageEnemiesInRadius() {
        enemyTargeter.getEnemiesInRadius(getPosition(), EXPLOSION_RADIUS).forEach(e -> {
            double dist = e.getPos().minus(getPosition()).getDist();
            double damageFactor = (EXPLOSION_RADIUS - dist) / EXPLOSION_RADIUS;
            e.damage((int) (damage * damageFactor));
        });
    }
}
