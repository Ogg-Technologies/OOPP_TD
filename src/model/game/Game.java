package model.game;

import model.game.enemy.EnemyHandler;
import model.game.enemy.EnemyService;
import model.game.enemy.ImmutableEnemy;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.tower.ImmutableTower;
import model.game.tower.TowerHandler;
import model.game.tower.TowerService;
import utils.Vector;

import java.util.List;

public class Game implements TowerService, EnemyService {
    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;

    public Game() {
        towerHandler = new TowerHandler(this);
        enemyHandler = new EnemyHandler(this);
    }

    public void update() {
        towerHandler.update();
        enemyHandler.update();
    }

    public Tile[][] getTileMap() {
        TileMap tileMapCopy = TileMap.fromDefaultTileGrid();
        return tileMapCopy.getTileGrid();
    }

    public List<? extends ImmutableTower> getTowers() {
        return towerHandler.getTowers();
    }

    public List<? extends ImmutableEnemy> getEnemies() {
        return enemyHandler.getEnemies();
    }

    @Override
    public Vector getFirstTargetPosition() {
        return tileMap.getStartPosition();
    }

    @Override
    public Vector getNextTargetPosition(Vector currentTargetPosition) {
        return tileMap.getNextInPath(currentTargetPosition);
    }
}
