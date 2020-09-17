package model.game.tower;

import model.game.tower.concretetowers.BasicTower;
import model.game.tower.concretetowers.GrizzlyBear;
import utils.Vector;

public class TowerFactory {

    private final TowerService towerService;

    public TowerFactory(TowerService towerService) {
        this.towerService = towerService;
    }

    public Tower createBasicTower(Vector pos) {
        return new BasicTower(new DefaultTower(towerService, pos));
    }

    public Tower createGrizzlyBear(Vector pos) {
        return new GrizzlyBear(new AimingTower(towerService, pos));
    }
}
