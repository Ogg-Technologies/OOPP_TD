package model.game.tower;

import model.game.tower.concretetowers.BasicTower;

public class TowerFactory {

    private final TowerService towerService;

    public TowerFactory(TowerService towerService) {
        this.towerService = towerService;
    }

    public Tower createBasicTower() {
        return new BasicTower(towerService);
    }
}
