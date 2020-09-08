package model;

import model.game.map.Tile;

public interface ModelData {
    Tile[][] getTileMap();   // TODO: Make method return TileMap instead to make it immutable and to remove copying matrix?
}
