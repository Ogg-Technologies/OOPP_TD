package model.game.tower.concretetowers;

import model.game.tower.DefaultTower;
import model.game.tower.Tower;
import model.game.tower.TowerService;

public class BasicTower implements Tower {

    private final Tower baseTower;

    public BasicTower(TowerService towerService) {
        baseTower = new DefaultTower(towerService);
    }

    @Override
    public TowerService getTowerService() {
        return baseTower.getTowerService();
    }

    @Override
    public void update() {

    }
}
