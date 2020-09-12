package model;

import model.game.Game;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.tower.ImmutableTower;

import java.util.List;

public final class Model implements ModelEventHandler, ModelData, Updatable {
    private Game game;

    public Model() {
        game = new Game();
    }

    @Override
    public void update() {
        game.update();
    }

    @Override
    public Tile[][] getTileMap() {
        return this.game.getTileMap();
    }

    @Override
    public List<? extends ImmutableTower> getTowers() {
        return game.getTowers();
    }

    @Override
    public List<? extends Enemy> getEnemies() {
        return game.getEnemies();
    }
}
