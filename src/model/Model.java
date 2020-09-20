package model;

import model.event.EventListener;
import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.projectile.Projectile;
import model.game.tower.Tower;
import utils.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Model implements ModelEventHandler, ModelData, Updatable {
    private Game game;
    private final Set<OnModelUpdateObserver> updateObservers;
    private final Set<EventListener> eventListeners;

    public Model() {
        game = new Game();
        updateObservers = new HashSet<>();
        eventListeners = new HashSet<>();
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
    public void addOnModelEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    @Override
    public Vector getMapSize() {
        return game.getMapSize();
    }

    @Override
    public Tile getTile(int x, int y) {
        return game.getTile(x, y);
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

    @Override
    public List<? extends Projectile> getProjectiles() {
        return game.getProjectiles();
    }


    /**
     *
     * @param v is a vector that can be null if the click event is not on the map
     */
    @Override
    public void clickedTile(Vector v) {
        if(v == null)
            return;
        //TODO: The vector is the tile that the user clicked on. For example (1, 1)
        System.out.println("x: " + v.getX() + ", y: " + v.getY());
    }
}
