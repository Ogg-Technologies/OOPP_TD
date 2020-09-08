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

    //TODO: positioning
    @Override
    public int getXPos() {
        return 0;
    }

    @Override
    public int getYPos() {
        return 0;
    }

    @Override
    public void update() {

    }
}
