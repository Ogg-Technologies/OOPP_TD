package model.game.tower.concretetowers;

import java.util.List;

import model.game.enemy.ImmutableEnemy;
import model.game.tower.Tower;
import model.game.tower.TowerService;

import utils.Vector;

public class BasicTower implements Tower {

    private final Tower baseTower;
    private final float range;

    public BasicTower(Tower baseTower) {
        this.baseTower = baseTower;
        this.range = 5;
    }

    @Override
    public TowerService getTowerService() {
        return baseTower.getTowerService();
    }

    @Override
    public Vector getPos() {
        return baseTower.getPos();
    }

    @Override
    public void update() {
        baseTower.update();
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public List<? extends ImmutableEnemy> getEnemiesInRange(float range) {
        return baseTower.getEnemiesInRange(range);
    }
}
