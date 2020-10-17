package model.game.tower.concretetowers;


import config.Config;
import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.tower.AbstractAttackingTower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.chargestrategy.BurstChargeStrategy;
import utils.Vector;

/**
 * @author Behroz
 * A tower with short range and attacks in bursts. Uses hitscan to damage enemies instead of creating a projectile
 */
public class SovietBear extends AbstractAttackingTower {

    private EventSender eventSender;

    public SovietBear(Vector pos, EnemyTargeter enemyTargeter, EventSender eventSender) {
        super(pos, Config.INSTANCE.SOVIET_BEAR.RANGE,
                new BurstChargeStrategy(Config.INSTANCE.SOVIET_BEAR.ATTACK_DELAY,
                        Config.INSTANCE.SOVIET_BEAR.ATTACKS_PER_BURST, Config.INSTANCE.SOVIET_BEAR.TICKS_BETWEEN_ATTACKS), enemyTargeter);
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        e.damage((int) (Config.INSTANCE.SOVIET_BEAR.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier()));
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
