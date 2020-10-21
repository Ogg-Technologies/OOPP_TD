package model.game.projectile.concreteprojectile;

import config.Config;
import model.event.Event;
import model.event.EventSender;
import model.game.economy.MoneyAdder;
import model.game.enemy.Enemy;
import model.game.health.Healable;
import model.game.projectile.AbstractProjectile;
import utils.Vector;

import java.util.Random;

/**
 * @author Behroz, Erik
 * <p>
 * Heatseeking projectile that on hit besides damaging the enemy also give player either 1 hp or 1 coin
 */
public class Bee extends AbstractProjectile {

    private final int damage;
    private final int healAmount;
    private final int moneyAmount;
    private final EventSender eventSender;
    private final Healable healable;
    private final MoneyAdder moneyAdder;
    private Enemy target;


    /**
     * Creates a bee
     *
     * @param position    Current postion on map
     * @param target      Bee's target
     * @param damage      Amount of damage target will take
     * @param eventSender Lets bee talk to view
     */
    public Bee(Vector position, Enemy target, int damage, int healAmount, int moneyAmount, EventSender eventSender, Healable healable, MoneyAdder moneyAdder) {
        super(position, target.getPos().minus(position).setMagnitude(Config.BombardaCharm.SPEED));
        this.damage = damage;
        this.healAmount = healAmount;
        this.moneyAmount = moneyAmount;
        this.eventSender = eventSender;
        this.target = target;
        this.healable = healable;
        this.moneyAdder = moneyAdder;
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

    /**
     * On enemy hit the method first checks if users health is at max.
     * If yes then it just adds money to the users economy.
     * If not then it's a fifty-percent chance between it increasing the users health and it giving the user money
     *
     * @param enemy
     */
    @Override
    public void onEnemyHit(Enemy enemy) {
        eventSender.sendEvent(new Event(Event.Type.PROJECTILE_HIT, this.getClass(), getPosition()));
        enemy.damage(damage);
        if (healable.isHealthEqualToMax()) {
            moneyAdder.addMoney(moneyAmount);
        } else {
            Random random = new Random();
            boolean b = random.nextBoolean();
            if (b) {
                healable.addHealth(healAmount);
            } else {
                moneyAdder.addMoney(moneyAmount);
            }
        }
        consumed = true;
    }
}
