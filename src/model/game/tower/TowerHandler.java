package model.game.tower;

import java.util.ArrayList;
import java.util.List;

public class TowerHandler {

    private final TowerFactory factory;
    private final List<Tower> towers;

    public TowerHandler(TowerService towerService) {
        factory = new TowerFactory(towerService);
        towers = new ArrayList<>();
    }
}
