package model.game;

import application.Constant;
import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyFactory;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;
import model.game.projectile.ProjectileHandler;
import model.game.tower.AbstractTowerFactory;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import model.game.tower.TowerHandler;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.TowerFinder;
import model.game.wave.WaveHandler;
import utils.Vector;

import java.util.Collection;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * The class representing the whole TowerDefenseGame with a given map.
 * It is created and used by Model
 */
public class Game implements EnemyGetter, EventListener {

    private final TileMap tileMap;
    private final MutableHealth baseHealth = new MutableHealth(Constant.getInstance().PLAYER.START_HEALTH);
    private final TowerHandler towerHandler = new TowerHandler();
    private final WaveHandler waveHandler;
    private final ProjectileHandler projectileHandler;
    private final Economy economy = new Economy(Constant.getInstance().PLAYER.START_MONEY);
    private final TowerFactory towerFactory;

    public Game(TileMap tileMap, EventSender eventSender) {
        this.tileMap = tileMap;
        EnemyFactory enemyFactory = new EnemyFactory(baseHealth::damage, tileMap.getPath());
        waveHandler = new WaveHandler(enemyFactory, eventSender);

        ProjectileFactory projectileFactory = new ProjectileFactory(eventSender, new EnemyTargeter(this));
        projectileHandler = new ProjectileHandler(projectileFactory, tileMap.getSize(), this);

        towerFactory = new TowerFactory(this, new TowerFinder(towerHandler::getTowers), projectileHandler, eventSender);
    }

    public void update() {
        if (baseHealth.isDead()) {
            return;
        }
        projectileHandler.update();
        towerHandler.update();
        waveHandler.update();
    }

    public Collection<? extends Tower> getTowers() {
        return towerHandler.getTowers();
    }

    public Vector getMapSize() {
        return tileMap.getSize();
    }

    public Collection<? extends Enemy> getEnemies() {
        return waveHandler.getSpawnedEnemies();
    }

    public Health getBaseHealth() {
        return baseHealth;
    }

    public Collection<Projectile> getProjectiles() {
        return projectileHandler.getProjectiles();
    }

    /**
     * Checks whether there are other towers or objects where other towers cannot be placed
     * If obstacles are added, add logic for them in this method
     *
     * @param tilePos the tileMap pos to look att
     * @return a boolean whether or not the tile is valid
     */
    public boolean isValidTile(Vector tilePos) {
        return !towerHandler.isTowerAt(tilePos) &&
                tileMap.getTile(tilePos.getIntX(), tilePos.getIntY()) == Tile.GROUND;
    }

    /**
     * @return the current amount of money units the player has
     */
    public int getMoney() {
        return economy.getMoney();
    }

    /**
     * Method for collecting position to place tower at.
     * Only places towers if requirement are fulfilled, such as no other tower on this tile
     *
     * @param towerType,   the towerType to be placed
     * @param towerFactory the interface that creates the tower
     * @param pos,         the position to place the tower
     * @see #isValidTile(Vector) for requirements
     */
    public void placeTower(Class<? extends Tower> towerType, AbstractTowerFactory towerFactory, Vector pos) {
        if (isValidTile(pos)) {
            if (economy.buyTower(towerType)) {
                towerHandler.createTower(towerFactory, pos);
            }
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == Event.Type.ENEMY_DEATH) {
            economy.addMoney(event.getSender());
        }
    }

    /**
     * Starts the next waves and gives extra money based on the remaining health of all activated waves and enemies on the map.
     */
    public void startNewWave() {
        economy.addMoney(waveHandler.getRewardForStartingWaveEarly());
        waveHandler.startNewWave();
    }

    public Health getEnemyAttackHealth() {
        return waveHandler.getEnemyAttackHealth();
    }

    /**
     * Getter for towerFactory to create interfaces of type AbstractTowerFactory
     *
     * @return the towerFactory
     */
    public TowerFactory getFactory() {
        return towerFactory;
    }

    /**
     * Getter for current wave number
     *
     * @return the current wave number
     */
    public int getWaveNumber() {
        return waveHandler.getCurrentLevel();
    }

    /**
     * Getter for games tileMap
     *
     * @return a tileMap
     */
    public TileMap getMap() {
        return tileMap;
    }
}
