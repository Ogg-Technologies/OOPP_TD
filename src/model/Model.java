package model;

import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.Game;
import model.game.Health;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.projectile.Projectile;
import model.game.tower.AbstractTowerFactory;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import utils.Vector;

import java.util.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * This represents the inner logic of the program. It keeps track of the state of the application.
 */
public final class Model implements ModelInputListener, ModelData, Updatable, EventSender {

    private final Set<EventListener> eventListeners = new HashSet<>();
    private final List<Command> commands = new ArrayList<>();
    private final List<? extends TileMap> maps;
    private Game game;

    public Model(List<? extends TileMap> maps) {
        this.maps = maps;
        game = new Game(maps.get(0), this);
        addOnModelEventListener(game);
    }

    @Override
    public void update() {
        executeCommands();

        game.update();

        sendEvent(new Event(Event.Type.UPDATE, this.getClass()));
    }

    private void executeCommands() {
        synchronized (commands) {
            commands.forEach(Command::execute);
            commands.clear();
        }
    }

    @Override
    public void addOnModelEventListener(EventListener eventListener) {
        synchronized (commands) {
            commands.add(() -> eventListeners.add(eventListener));
        }
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
        synchronized (commands) {
            commands.add(() -> game.placeTower(towerType, factory, pos));
        }
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
        synchronized (commands) {
            commands.add(() -> game.startNewWave());
        }
    }

    @Override
    public TowerFactory getFactory() {
        return game.getFactory();
    }

    @Override
    public Health getEnemyAttackHealth() {
        return game.getEnemyAttackHealth();
    }

    @Override
    public int getWaveNumber() {
        return game.getWaveNumber();
    }

    @Override
    public TileMap[] getTileMaps() {
        return maps.toArray(new TileMap[0]);
    }

    /**
     * @author Oskar, Erik
     * <p>
     * Command design pattern for delaying the execution of changes to the model from the Controller
     */
    @FunctionalInterface
    private interface Command {
        void execute();
    }
}
