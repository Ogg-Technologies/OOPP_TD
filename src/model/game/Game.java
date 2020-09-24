package model.game;

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
import model.game.tower.TowerService;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game implements TowerService, ProjectileService {
    private final EventSender eventSender;

    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;
    private final MutableHealth baseHealth;
    private final List<Projectile> projectiles;
    private final ProjectileFactory projectileFactory;

    public Game(EventSender eventSender) {
        this.eventSender = eventSender;
        towerHandler = new TowerHandler(this, eventSender);
        baseHealth = new MutableHealth(100);
        enemyHandler = new EnemyHandler(baseHealth::damage, tileMap.getPath());
        projectiles = new ArrayList<>();
        projectileFactory = new ProjectileFactory(this);
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

    public List<? extends Tower> getTowers() {
        return towerHandler.getTowers();
    }

    @Override
    public Vector getMapSize() {
        return tileMap.getSize();
    }

    public List<? extends Enemy> getEnemies() {
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
    public List<Projectile> getProjectiles() {
        return Collections.unmodifiableList(projectiles);
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
                tileMap.getTile(tilePos.getX(), tilePos.getY()) == Tile.GROUND; //TODO: if we decide to have more obstacle, add it here
    }

    //TODO: this method is what gives view the money information
    public int getMoney() {
        return 999999;
    }
}
