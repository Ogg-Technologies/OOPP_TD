package model;

import model.game.Game;
import model.game.map.Tile;

public final class Model implements ModelEventHandler, ModelData, Updatable {
    private Game game;

    public Model() {
        game = new Game();
    }

    @Override
    public void update() {
    }

    @Override
    public Tile[][] getTileMap() {
        return this.game.getTileMap();
    }
}
