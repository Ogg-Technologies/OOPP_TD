package model;

import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.tower.Tower;
import model.particles.EmitterCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Model implements ModelEventHandler, ModelData, Updatable {
    private Game game;
    private final Set<OnModelUpdateObserver> updateObservers;
    private EmitterCreator emitterCreator;

    public Model() {
        game = new Game();
        updateObservers = new HashSet<>();
    }

    /**
     * Sets the emitter creator for the model. The model tells the EmitterCreator when and where a particle emitter
     * should be created
     * @param emitterCreator The emitter creator that the model should use to create particle emitters
     */
    public void setEmitterCreator(EmitterCreator emitterCreator) {
        this.emitterCreator = emitterCreator;
    }

    @Override
    public void update() {
        game.update();

        for (OnModelUpdateObserver observer : updateObservers) {
            observer.onUpdate();
        }
    }

    /**
     * Adds the observer to the list of on model update observers
     * that gets fired whenever the model has updated
     * @param observer Observer to be added
     */
    @Override
    public void addOnModelUpdateObserver(OnModelUpdateObserver observer) {
        updateObservers.add(observer);
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
