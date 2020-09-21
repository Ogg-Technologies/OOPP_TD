package model.game.tower;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;

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
    public void update() {}

    public List<? extends Enemy> getEnemiesInRange(double range) {
        List<? extends Enemy> allEnemies = towerService.getEnemies();
        List<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy currentEnemy : allEnemies) {
            if (getPos().minus(currentEnemy.getPos()).getDist() <= range) {
                enemiesInRange.add(currentEnemy);
            }
        }
        return enemiesInRange;
    }

    public double getRange() {
        return 0;
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
