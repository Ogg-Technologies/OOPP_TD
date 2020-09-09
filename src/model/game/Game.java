package model.game;

import model.game.enemy.EnemyHandler;
import model.game.enemy.EnemyService;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.tower.ImmutableTower;
import model.game.tower.TowerHandler;
import model.game.tower.TowerService;

import java.util.List;

public class Game implements TowerService, EnemyService {
    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;

    public Game() {
        towerHandler = new TowerHandler(this);
        enemyHandler = new EnemyHandler(this);
        System.out.println(tileMap.getTile(1, 1));
    }

    public Tile[][] getTileMap() {
        TileMap tileMapCopy = TileMap.fromDefaultTileGrid();
        return tileMapCopy.getTileGrid();
    }

    public List<? extends ImmutableTower> getTowers() {
        return towerHandler.getTowers();
    }
}
