package model.game.tower.concretetowers;


import application.Constant;
import model.event.Event;
import model.event.EventSender;
import model.game.tower.AbstractBuffTower;
import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.TowerFinder;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar
 * A tower that periodically increases the damage but decreases range and fire rate of nearby towers.
 */
public class Beer extends AbstractBuffTower {

    private EventSender eventSender;

    public Beer(Vector pos, TowerFinder towerFinder, EventSender eventSender) {
        super(pos, Constant.getInstance().BEER.RANGE,
                new ConstantChargeStrategy(Constant.getInstance().BEER.ATTACK_DELAY), towerFinder);
        this.eventSender = eventSender;
    }

    @Override
    protected void buffTowers(Collection<? extends Tower> towers) {
        final int duration = Constant.getInstance().BEER.BUFF_DURATION;
        towers.forEach(tower -> {
            tower.applyBuff(m -> m.addDamage(Constant.getInstance().BEER.DAMAGE_BUFF_PERCENTAGE), duration);
            tower.applyBuff(m -> m.addRange(Constant.getInstance().BEER.RANGE), duration);
            tower.applyBuff(m -> m.addFireRate(Constant.getInstance().BEER.FIRE_RATE_BUFF_PERCENTAGE), duration);
        });
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), 0));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
