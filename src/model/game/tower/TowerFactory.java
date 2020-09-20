package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.BasicTower;
import model.game.tower.concretetowers.GrizzlyBear;
import utils.Vector;

public class TowerFactory {

    private final TowerService towerService;
    private final EventSender eventSender;

    public TowerFactory(TowerService towerService, EventSender eventSender) {
        this.towerService = towerService;
        this.eventSender = eventSender;
    }

    public Tower createBasicTower(Vector pos) {
        return new BasicTower(new DefaultTower(towerService, pos));
    }

    public Tower createGrizzlyBear(Vector pos) {
        return new GrizzlyBear(new AimingTower(towerService, pos), eventSender);
    }
}
