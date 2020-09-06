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

    @Override
    public void update() {

    }
}
