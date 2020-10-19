package model.game.projectile.concreteprojectile;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.AbstractProjectile;
import model.game.projectile.ProjectileService;
import model.game.tower.towerutils.EnemyTargeter;
import utils.Vector;

/**
 * @author Behroz
 * Projectile used by Bazooka Bear
 */
public class Rocket extends AbstractProjectile {

    public static final double EXPLOSION_RADIUS = 2.0;

    private final int damage;
    private final EventSender eventSender;
    private final EnemyTargeter enemyTargeter;


    public Rocket(ProjectileService service, Vector position, Vector velocity, int damage, EventSender eventSender, EnemyTargeter enemyTargeter) {
        super(service, position, velocity);
        this.damage = damage;
        this.eventSender = eventSender;
        this.enemyTargeter = enemyTargeter;

    }

    @Override
    protected void onEnemyHit(Enemy enemy) {
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
