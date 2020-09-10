package model.game.tower;

import java.util.ArrayList;
import java.util.List;

import model.game.enemy.ImmutableEnemy;
import utils.Vector;

public class DefaultTower implements Tower {

    private final TowerService towerService;
    private final Vector pos;

    public DefaultTower(TowerService towerService, Vector pos) {
        this.towerService = towerService;
        this.pos = pos;
    }

    @Override
    public TowerService getTowerService() {
        return towerService;
    }

    @Override
    public Vector getPos() {
        return pos;
    }

    @Override
    public void update() {

    }

    public List<? extends ImmutableEnemy> getEnemiesInRange(float range) {
        List<? extends ImmutableEnemy> allEnemies = towerService.getEnemies();
        List<ImmutableEnemy> enemiesInRange = new ArrayList<>();
        for (ImmutableEnemy currentEnemy : allEnemies) {
            if (getPos().minus(currentEnemy.getPos()).getDist() <= range) {
                enemiesInRange.add(currentEnemy);
            }
        }
        return enemiesInRange;
    }

    public float getRange() {
        return 0;
    }
}
