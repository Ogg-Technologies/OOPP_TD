package model.game.tower.concretetowers;

import model.game.enemy.Enemy;
import model.game.tower.Tower;
import model.game.tower.TowerService;
import model.game.tower.TowerVisitor;
import utils.Vector;

import java.util.List;
import java.util.Random;

public class BasicTower implements Tower {

    private final Tower baseTower;
    private final float range;

    private int charge;
    private final static int MAXCHARGE = 88;

    public BasicTower(Tower baseTower) {
        this.baseTower = baseTower;
        this.range = 5;
        this.charge = 87;
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
        charge++;
        baseTower.update();
        if (charge >= MAXCHARGE) {
            attackRandomEnemy();
            charge =0;
        }
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public List<? extends Enemy> getEnemiesInRange(float range) {
        return baseTower.getEnemiesInRange(range);
    }

    public void attackRandomEnemy() {
        List<? extends Enemy> enemiesInRange = getEnemiesInRange(range);
        if (!enemiesInRange.isEmpty()) {
            Random rand = new Random();
            enemiesInRange.get(rand.nextInt(enemiesInRange.size())).damage(1);
        }
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }


}
