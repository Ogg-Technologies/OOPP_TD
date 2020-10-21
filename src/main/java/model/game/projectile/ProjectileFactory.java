package model.game.projectile;

import config.Config;
import model.event.EventSender;
import model.game.economy.MoneyAdder;
import model.game.enemy.Enemy;
import model.game.health.Healable;
import model.game.projectile.concreteprojectile.Bee;
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
    private final MoneyAdder moneyAdder;
    private final Healable healable;

    public ProjectileFactory(EventSender eventSender, EnemyTargeter enemyTargeter, Healable healable, MoneyAdder moneyAdder) {
        this.eventSender = eventSender;
        this.enemyTargeter = enemyTargeter;
        this.healable = healable;
        this.moneyAdder = moneyAdder;


    }

    public Projectile createRock(Vector position, Vector target, int damage) {
        return new Rock(position, target.minus(position).setMagnitude(Config.Rock.SPEED), damage, this.eventSender);
    }

    public Projectile createExplodingCharm(Vector position, Enemy target, int damage) {
        return new BombardaCharm(position, target, damage, eventSender, enemyTargeter);
    }

    public Projectile createRocket(Vector position, Vector target, int damage) {
        return new Rocket(position, target.minus(position).setMagnitude(Config.Rocket.SPEED), damage, this.eventSender, this.enemyTargeter);
    }

    public Projectile createBee(Vector position, Enemy target, int damage, int healAmount, int moneyAmount) {
        return new Bee(position, target, damage, healAmount, moneyAmount, eventSender, healable, moneyAdder);
    }
}
