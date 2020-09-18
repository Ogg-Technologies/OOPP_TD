package model;

import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.tower.Tower;

import java.util.List;

public interface ModelData {
    void addOnModelUpdateObserver(OnModelUpdateObserver observer);

    Tile[][] getTileMap();   // TODO: Make method return TileMap instead to make it immutable and to remove copying matrix?

    List<? extends Tower> getTowers();

    List<? extends Enemy> getEnemies();

    Health getBaseHealth();
}
