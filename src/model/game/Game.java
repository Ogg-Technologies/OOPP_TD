package model.game;

import model.game.enemy.EnemyHandler;
import model.game.enemy.EnemyService;
import model.game.map.Map;
import model.game.tower.TowerHandler;
import model.game.tower.TowerService;

public class Game implements TowerService, EnemyService {
    private final Map map = Map.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;

    public Game() {
        towerHandler = new TowerHandler(this);
        enemyHandler = new EnemyHandler(this);
        System.out.println(map.getTile(1, 1));
    }
}
