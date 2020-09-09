package model;

import model.game.map.Tile;
import model.game.tower.ImmutableTower;

import java.util.List;

public interface ModelData {
    Tile[][] getTileMap();   // TODO: Make method return TileMap instead to make it immutable and to remove copying matrix?

    List<? extends ImmutableTower> getTowers();
}
