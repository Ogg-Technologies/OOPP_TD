package model.game;

import application.Constant;
import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;
import model.game.projectile.ProjectileService;
import model.game.tower.Tower;
import model.game.tower.TowerHandler;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import model.game.wave.WaveHandler;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * The class representing the whole TowerDefenseGame with a given map.
 * It is created and used by Model
 */
public class Game implements EnemyGetter, ProjectileCreator, ProjectileService, EventListener {
    private final EventSender eventSender;

    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final WaveHandler waveHandler;
    private final MutableHealth baseHealth;
    private final Collection<Projectile> projectiles;
    private final ProjectileFactory projectileFactory;
    private final Economy economy;

    public Game(EventSender eventSender) {
        this.eventSender = eventSender;
        towerHandler = new TowerHandler(this, this, eventSender);
        baseHealth = new MutableHealth(Constant.getInstance().PLAYER.START_HEALTH);
        waveHandler = new WaveHandler(baseHealth::damage, tileMap.getPath(), eventSender);
        projectiles = new ArrayList<>();
        projectileFactory = new ProjectileFactory(this, eventSender, new EnemyTargeter(this));
        economy = new Economy(Constant.getInstance().PLAYER.START_MONEY);
    }

    public void update() {
        if (baseHealth.isDead()) {
            return;
        }
        updateProjectiles();
        towerHandler.update();
        waveHandler.update();
    }

    private void updateProjectiles() {
        for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
            Projectile p = iterator.next();
            p.update();
            if (p.isConsumed()) {
                iterator.remove();
            }
        }
    }

    public Collection<? extends Tower> getTowers() {
        return towerHandler.getTowers();
    }

    @Override
    public Vector getMapSize() {
        return tileMap.getSize();
    }

    public Collection<? extends Enemy> getEnemies() {
        return waveHandler.getSpawnedEnemies();
    }

    public Health getBaseHealth() {
        return baseHealth;
    }

    @Override
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    @Override
    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    /**
     * Returns the Tile at the given position or throws an exception if it is not within map bounds
     */
    public Tile getTile(int x, int y) {
        return tileMap.getTile(x, y);
    }

    //TODO: Change this if not compliant with what is wanted in the model
    public Collection<Projectile> getProjectiles() {
        return Collections.unmodifiableCollection(new ArrayList<>(projectiles));
    }

    /**
     * Checks whether there are other towers or objects where other towers cannot be placed
     *
     * @param tilePos the tileMap pos to look att
     * @return a boolean whether or not the tile is valid
     */
//    public boolean isValidTile(@NotNull Vector tilePos) {
    public boolean isValidTile(Vector tilePos) {
        return !towerHandler.isTowerAt(tilePos) &&
                tileMap.getTile(tilePos.getIntX(), tilePos.getIntY()) == Tile.GROUND; //TODO: if we decide to have more obstacle, add it here
    }

    //TODO: this method is what gives view the money information
    public int getMoney() {
        return economy.getMoney();
    }

    /**
     * Method for collecting position to place tower at.
     * Only places towers if requirement are fulfilled, such as no other tower on this tile
     *
     * @param v,          the position to place the tower
     * @param towerClass, the tower to be placed
     * @see #isValidTile(Vector) for requirements
     */
    public void placeTower(Vector v, Class<? extends Tower> towerClass) {
        //TODO: Logic for if the tower can be placed, and what tower to place
        if (isValidTile(v)) {
            if (economy.buyTower(towerClass)) {
                towerHandler.createTower(v, towerClass);
            }
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == Event.Type.ENEMY_DEATH) {
            economy.addMoney(event.getSender());
        }
    }

    public void startNewWave() {
        int remainingEnemyHealth = waveHandler.getEnemyAttackHealth().getCurrent();
        economy.addMoney(remainingEnemyHealth);
        waveHandler.startNewWave();
    }

    /**
     * Gets the price of desired tower.
     *
     * @param towerClass desired tower.
     * @return returns the price.
     */
    public int getTowerPrice(Class<? extends Tower> towerClass) {
        return economy.getTowerPrice(towerClass);
    }

    /**
     * Get start range of specified tower
     *
     * @param towerClass the specified tower
     * @return the range
     */
    public double getRangeOfTower(Class<? extends Tower> towerClass) {
        return towerHandler.getRangeOfTower(towerClass);
    }

    public Health getEnemyAttackHealth() {
        return waveHandler.getEnemyAttackHealth();
    }
}
