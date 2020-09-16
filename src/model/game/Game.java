package model.game;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyHandler;
import model.game.enemy.EnemyService;
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
import java.util.Iterator;
import java.util.List;

public class Game implements TowerService, EnemyService, ProjectileService {
    private final TileMap tileMap = TileMap.fromDefaultTileGrid();
    private final TowerHandler towerHandler;
    private final EnemyHandler enemyHandler;
    private final MutableHealth baseHealth;
    private final List<Projectile> projectiles;
    private final ProjectileFactory projectileFactory;

    public Game() {
        towerHandler = new TowerHandler(this);
        enemyHandler = new EnemyHandler(this, tileMap.getPath());
        baseHealth = new MutableHealth(100);
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
        for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext() ;) {
            Projectile p = iterator.next();
            p.update();
            if (p.isConsumed()) {
                iterator.remove();
            }
        }
    }

    public Tile[][] getTileMap() {
        TileMap tileMapCopy = TileMap.fromDefaultTileGrid();
        return tileMapCopy.getTileGrid();
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

    @Override
    public void damageBase(int current) {
        baseHealth.damage(current);
    }

    public Health getBaseHealth() {
        return baseHealth;
    }
}
