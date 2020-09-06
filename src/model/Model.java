package model;

import model.game.Game;

public final class Model implements ModelEventHandler, ModelData, Updatable {
    private Game game;

    public Model() {
        game = new Game();
    }

    @Override
    public void update() {

    }
}
