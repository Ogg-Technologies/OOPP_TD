package model.game.projectile;

import application.Constant;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import model.game.tower.towerutils.EnemyTargeter;
import utils.Vector;

/**
 * @author Oskar, Behroz, Samuel, Erik
 * Factory for creating Projectiles. Used by Game.
 */
public final class ProjectileFactory {

    private final ProjectileService service;
    private final EventSender eventSender;
    private final EnemyTargeter enemyTargeter;

    public ProjectileFactory(ProjectileService service, EventSender eventSender, EnemyTargeter enemyTargeter) {
        this.service = service;
        this.eventSender = eventSender;
        this.enemyTargeter = enemyTargeter;
    }

    public Projectile createRock(Vector position, Vector target, int damage) {
        return new Rock(service, position, target.minus(position).setMagnitude(Constant.getInstance().PROJECTILE_SPEED.ROCK), damage, this.eventSender);
    }

    public Projectile createExplodingCharm(Vector position, Enemy target, int damage) {
        return new BombardaCharm(service, position, target, damage, eventSender, enemyTargeter);
    }
}
