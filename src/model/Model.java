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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Model implements ModelInputListener, ModelData, Updatable, EventSender {
    private Game game;
    private final Set<EventListener> eventListeners;

    public Model() {
        game = new Game(this);
        eventListeners = new HashSet<>();
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
     * @param v is a vector that correspond to a position on the tileMap
     */
    @Override
    public void onTileClick(Vector v) {
        //TODO: The vector is the tile that the user clicked on. For example (1, 1)
        System.out.println("x: " + v.getIntX() + ", y: " + v.getIntY());
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
    public void sendEvent(Event event) {
        for (EventListener listener : eventListeners) {
            listener.onEvent(event);
        }
    }
}
