package model;

import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.projectile.Projectile;
import model.game.tower.AbstractTowerFactory;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
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


    @Override
    public void onTileClick(AbstractTowerFactory factory, Vector pos, Class<? extends Tower> towerType) {
        game.placeTower(towerType, factory, pos);
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

    @Override
    public void onStartNewWave() {
        game.startNewWave();
    }

    @Override
    public TowerFactory getFactory() {
        return game.getFactory();
    }

    @Override
    public Health getEnemyAttackHealth() {
        return game.getEnemyAttackHealth();
    }
}
