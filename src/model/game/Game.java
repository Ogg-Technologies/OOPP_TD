package model.game;

import model.event.Event;
import model.event.EventListener;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyHandler;
import model.game.map.Tile;
import model.game.map.TileMap;
import model.game.projectile.Projectile;
import model.game.projectile.ProjectileFactory;
import model.game.projectile.ProjectileService;
import model.game.tower.Tower;
import model.game.tower.TowerHandler;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.SniperBear;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

import java.util.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * The class representing the whole TowerDefenseGame with a given map.
 * It is created and used by Model
 */
public class Game implements EnemyGetter, ProjectileCreator, ProjectileService, EventListener {
    private final EventSender eventSender;

    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;
    private final MutableHealth baseHealth;
    private final Collection<Projectile> projectiles;
    private final ProjectileFactory projectileFactory;
    private final Economy economy;

    public Game(EventSender eventSender) {
        this.eventSender = eventSender;
        towerHandler = new TowerHandler(this, this, eventSender);
        baseHealth = new MutableHealth(100);
        enemyHandler = new EnemyHandler(baseHealth::damage, tileMap.getPath(), eventSender);
        projectiles = new ArrayList<>();
        projectileFactory = new ProjectileFactory(this, eventSender);
        economy = new Economy(1000);
    }

    public void update() {
        if (baseHealth.isDead()) {
            return;
        }
        updateProjectiles();
        towerHandler.update();
        enemyHandler.update();
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
        return enemyHandler.getEnemies();
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
     * @param v, the position to place the tower
     * @see #isValidTile(Vector) for requirements
     */
    public void placeTower(Vector v) {
        //TODO: Logic for if the tower can be placed, and what tower to place
        if (isValidTile(v)) {
            switch (new Random().nextInt(3)) {
                case 0:
                    if (economy.buyTower(GrizzlyBear.class)) {
                        towerHandler.createGrizzlyBear(v);
                    }
                    break;
                case 1:
                    if (economy.buyTower(BearryPotter.class)) {
                        towerHandler.createMageBear(v);
                    }
                    break;
                case 2:
                    if (economy.buyTower(SniperBear.class)) {
                        towerHandler.createSniperBear(v);
                    }
                    break;
            }

        }
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == Event.Type.ENEMY_DEATH) {
            economy.addMoney(event.getSender());
        }
    }

    public void startNewWave(){
        enemyHandler.startNewWave();
    }

    /**
     * Gets the price of desired tower.
     * @param towerClass desired tower.
     * @return returns the price.
     */
    public int getTowerPrice(Class<? extends Tower> towerClass) {
        return economy.getTowerPrice(towerClass);
    }
}
