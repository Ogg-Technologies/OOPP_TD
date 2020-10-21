package model.game.tower.concretetowers;


import config.Config;
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
 * A tower that periodically buffs the range of nearby towers.
 */
public class BearGrylls extends AbstractBuffTower {

    private final EventSender eventSender;

    public BearGrylls(Vector pos, TowerFinder towerFinder, EventSender eventSender) {
        super(pos, Config.BearGrylls.RANGE,
                new ConstantChargeStrategy(Config.BearGrylls.ATTACK_DELAY), towerFinder);
        this.eventSender = eventSender;
    }

    @Override
    protected void buffTowers(Collection<? extends Tower> towers) {
        towers.forEach(tower -> {
            tower.applyBuff(m -> m.addRange(Config.BearGrylls.BUFF_PERCENTAGE), Config.BearGrylls.BUFF_DURATION);
        });
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), 0));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
