package model.game.tower;

import utils.Vector;

public class DefaultTower implements Tower {

    private final TowerService towerService;
    private final Vector pos;

    public DefaultTower(TowerService towerService, Vector pos) {
        this.towerService = towerService;
        this.pos = pos;
    }

    @Override
    public TowerService getTowerService() {
        return towerService;
    }

    @Override
    public Vector getPos() {
        return pos;
    }

    @Override
    public void update() {

    }
}
