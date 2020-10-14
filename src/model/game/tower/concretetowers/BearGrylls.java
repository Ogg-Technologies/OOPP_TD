package model.game.tower.concretetowers;


import application.Constant;
import model.event.Event;
import model.event.EventSender;
import model.game.tower.AbstractTower;
import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.TowerFinder;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar
 * A tower that periodically buffs the range of nearby towers.
 */
public class BearGrylls extends AbstractTower {

    private TowerFinder towerFinder;
    private EventSender eventSender;

    public BearGrylls(Vector pos, TowerFinder towerFinder, EventSender eventSender) {
        super(pos, Constant.getInstance().BEAR_GRYLLS.RANGE,
                new ConstantChargeStrategy(Constant.getInstance().BEAR_GRYLLS.ATTACK_DELAY));
        this.towerFinder = towerFinder;
        this.eventSender = eventSender;
    }

    @Override
    protected boolean tryFire() {
        Collection<? extends Tower> towersInRange = towerFinder.getTowersInRange(getPos(), getRange());
        if (towersInRange.size() == 0) {
            return false;
        }
        towersInRange.forEach(tower -> {
            tower.applyBuff(m -> m.addRange(Constant.getInstance().BEAR_GRYLLS.BUFF_PERCENTAGE), Constant.getInstance().BEAR_GRYLLS.BUFF_DURATION);
        });
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), 0));
        return true;
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
