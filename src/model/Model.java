package model;

import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.projectile.Projectile;
import model.game.tower.Tower;
import utils.Vector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * This represents the inner logic of the program. It keeps track of the state of the application.
 */
public final class Model implements ModelInputListener, ModelData, Updatable, EventSender {
    private Game game;
    private final Set<EventListener> eventListeners;

    public Model() {
        game = new Game(this);
        eventListeners = new HashSet<>();
        addOnModelEventListener(game);
    }

    @Override
    public void update() {
        game.update();

        sendEvent(new Event(Event.Type.UPDATE, this.getClass()));
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
    public Collection<? extends Tower> getTowers() {
        return game.getTowers();
    }

    @Override
    public Collection<? extends Enemy> getEnemies() {
        return game.getEnemies();
    }

    @Override
    public Health getBaseHealth() {
        return game.getBaseHealth();
    }

    @Override
    public Collection<? extends Projectile> getProjectiles() {
        return game.getProjectiles();
    }


    /**
     * This method sends the vector through to the {@code Game} object
     * @param v is a vector that correspond to a position on the tileMap
     * @param towerClass the type of the tower to place in game
     */
    @Override
    public void onTileClick(Vector v, Class<? extends Tower> towerClass) {
        game.placeTower(v, towerClass);
    }

    @Override
    public boolean isValidTile(Vector tilePos) {
        return game.isValidTile(tilePos);
    }

    @Override
    public int getMoney() {
        return game.getMoney();
    }

    @Override
    public int getTowerPrice(Class<?extends Tower> towerClass) {
        return game.getTowerPrice(towerClass);
    }

    @Override
    public double getRangeOfTower(Class<? extends Tower> towerClass) {
        return game.getRangeOfTower(towerClass);
    }

    @Override
    public void sendEvent(Event event) {
        for (EventListener listener : eventListeners) {
            listener.onEvent(event);
        }
    }

    @Override
    public void onStartNewWave() {
        game.startNewWave();
    }
}
