package model.game.tower.concretetowers;

import model.game.tower.Tower;
import model.game.tower.TowerService;
import utils.Vector;

public class BasicTower implements Tower {

    private final Tower baseTower;

    public BasicTower(Tower baseTower) {
        this.baseTower = baseTower;
    }

    @Override
    public TowerService getTowerService() {
        return baseTower.getTowerService();
    }

    @Override
    public Vector getPos() {
        return baseTower.getPos();
    }

    @Override
    public void update() {
        baseTower.update();
    }
}
