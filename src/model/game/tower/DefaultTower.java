package model.game.tower;

public class DefaultTower implements Tower {

    private final TowerService towerService;

    public DefaultTower(TowerService towerService) {
        this.towerService = towerService;
    }

    @Override
    public TowerService getTowerService() {
        return towerService;
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
