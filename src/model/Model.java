package model;

import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.tower.Tower;

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
    public List<? extends Tower> getTowers() {
        return game.getTowers();
    }

    @Override
    public List<? extends Enemy> getEnemies() {
        return game.getEnemies();
    }

    @Override
    public Health getBaseHealth() {
        return game.getBaseHealth();
    }
}
