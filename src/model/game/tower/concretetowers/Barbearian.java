package model.game.tower.concretetowers;


import application.Constant;
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

    private EventSender eventSender;

    public Barbearian(Vector pos, EnemyTargeter enemyTargeter, EventSender eventSender) {
        super(pos, Constant.getInstance().BARBEARIAN.RANGE,
                new BurstChargeStrategy(Constant.getInstance().BARBEARIAN.ATTACK_DELAY,
                        Constant.getInstance().BARBEARIAN.ATTACKS_PER_BURST), enemyTargeter);
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        e.damage(Constant.getInstance().BARBEARIAN.BASE_DAMAGE);
        eventSender.sendEvent(new Event(Event.Type.TOWER_ATTACK, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
