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
 * A tower with melee attacks which go out in bursts. Does not create projectiles
 */
public class Barbearian extends AbstractAttackingTower {

    private final EventSender eventSender;

    public Barbearian(Vector pos, EnemyTargeter enemyTargeter, EventSender eventSender) {
        super(pos, Config.Barbearian.RANGE,
                new BurstChargeStrategy(Config.Barbearian.ATTACK_DELAY,
                        Config.Barbearian.ATTACKS_PER_BURST, Config.Barbearian.TICKS_BETWEEN_ATTACKS), enemyTargeter);
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        e.damage((int) (Config.Barbearian.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier()));
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
