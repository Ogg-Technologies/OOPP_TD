package model.game.tower.concretetowers;


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

    public static final int RANGE = 2;
    private static final int UPDATES_BETWEEN_ATTACKS = 80;
    private static final int ATTACKS_PER_BURST = 20;
    private static final int BASE_DAMAGE = 1;
    private EventSender eventSender;

    public SovietBear(Vector pos, EnemyTargeter enemyTargeter, EventSender eventSender) {
        super(pos, RANGE, new BurstChargeStrategy(UPDATES_BETWEEN_ATTACKS, ATTACKS_PER_BURST), enemyTargeter);
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        e.damage(BASE_DAMAGE);
        eventSender.sendEvent(new Event(Event.Type.TOWER_ATTACK, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
