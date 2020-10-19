package model.game.projectile;

import config.Config;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import model.game.projectile.concreteprojectile.Rocket;
import model.game.tower.towerutils.EnemyTargeter;
import utils.Vector;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Factory for creating Projectiles. Used by Game.
 */
public final class ProjectileFactory {

    private final EventSender eventSender;
    private final EnemyTargeter enemyTargeter;

    public ProjectileFactory(EventSender eventSender, EnemyTargeter enemyTargeter) {
        this.eventSender = eventSender;
        this.enemyTargeter = enemyTargeter;
    }

    public Projectile createRock(Vector position, Vector target, int damage) {
        return new Rock(position, target.minus(position).setMagnitude(Config.INSTANCE.ROCK.SPEED), damage, this.eventSender);
    }

    public Projectile createExplodingCharm(Vector position, Enemy target, int damage) {
        return new BombardaCharm(position, target, damage, eventSender, enemyTargeter);
    }

    public Projectile createRocket(Vector position, Vector target, int damage) {
        return new Rocket(position, target.minus(position).setMagnitude(Config.INSTANCE.ROCKET.SPEED), damage, this.eventSender, this.enemyTargeter);
    }
}
