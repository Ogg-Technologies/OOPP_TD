package model.game.tower.concretetowers;

import config.Config;
import model.event.Event;
import model.event.EventSender;
import model.game.economy.MoneyAdder;
import model.game.tower.AbstractTower;
import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.TowerFinder;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import utils.Vector;

import java.util.ArrayList;

/**
 * @author Behroz
 * <p>
 * Tower that gives player coins depending on number of towers nearby
 */
public class Bearon extends AbstractTower {
    private final MoneyAdder moneyAdder;
    private final EventSender eventSender;
    private final TowerFinder towerFinder;

    private final int coinsPerTowerNearby = Config.Bearon.COINS_PER_TOWER;


    /**
     * Creates Bearon tower
     *
     * @param pos         The tile position of the tower
     * @param moneyAdder  Gives tower access to addMoney method
     * @param towerFinder Gives tower access to getTowers method
     * @param eventSender Gives tower ability to send events
     */
    public Bearon(Vector pos, MoneyAdder moneyAdder, TowerFinder towerFinder, EventSender eventSender) {
        super(pos, Config.Bearon.RANGE, new ConstantChargeStrategy(Config.Bearon.ATTACK_DELAY));
        this.moneyAdder = moneyAdder;
        this.towerFinder = towerFinder;
        this.eventSender = eventSender;
    }

    @Override
    protected boolean tryFire() {
        ArrayList<Tower> towersNearby = new ArrayList<>(towerFinder.getTowersInRange(getPos(), getRange()));
        if (towersNearby.size() > 0) {
            moneyAdder.addMoney(coinsPerTowerNearby * towersNearby.size());
            eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), 0));
            return true;
        }
        return false;
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
