package model;

import model.game.Game;
import model.game.map.Tile;
import model.game.tower.Tower;
import model.game.tower.concretetowers.BasicTower;

import java.util.ArrayList;
import java.util.List;

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

    //TODO: make this correct
    @Override
    public List<Tower> getTowers() {
        List<Tower> tempList = new ArrayList<>();
        tempList.add(new BasicTower(game));
        return tempList;
    }
}
