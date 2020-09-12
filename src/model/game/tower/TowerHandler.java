package model.game.tower;

import utils.Vector;

import java.util.ArrayList;
import java.util.List;

public class TowerHandler {

    private final TowerFactory factory;
    private final List<Tower> towers;

    public TowerHandler(TowerService towerService) {
        factory = new TowerFactory(towerService);
        towers = new ArrayList<>();
        towers.add(factory.createBasicTower(new Vector(0, 0)));
    }

    public List<? extends Tower> getTowers() {
        return towers;
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
        }
    }
}
