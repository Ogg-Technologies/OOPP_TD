package model.game;

import model.game.map.Map;
import model.game.tower.TowerHandler;
import model.game.tower.TowerService;

public class Game implements TowerService {
    private final Map map = Map.fromDefaultTileGrid();
    private final TowerHandler towerHandler;

    public Game() {
        towerHandler = new TowerHandler(this);
        System.out.println(map.getTile(1, 1));
    }
}
