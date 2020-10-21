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
 * A tower that periodically increases the damage but decreases range and fire rate of nearby towers.
 */
public class Beer extends AbstractBuffTower {

    private final EventSender eventSender;

    public Beer(Vector pos, TowerFinder towerFinder, EventSender eventSender) {
        super(pos, Config.Beer.RANGE,
                new ConstantChargeStrategy(Config.Beer.ATTACK_DELAY), towerFinder);
        this.eventSender = eventSender;
    }

    @Override
    protected void buffTowers(Collection<? extends Tower> towers) {
        final int duration = Config.Beer.BUFF_DURATION;
        towers.forEach(tower -> {
            tower.applyBuff(m -> m.addDamage(Config.Beer.DAMAGE_BUFF_PERCENTAGE), duration);
            tower.applyBuff(m -> m.addRange(Config.Beer.RANGE_BUFF_PERCENTAGE), duration);
            tower.applyBuff(m -> m.addFireRate(Config.Beer.FIRE_RATE_BUFF_PERCENTAGE), duration);
        });
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), 0));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
