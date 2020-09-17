package model.game.tower;

import model.game.enemy.Enemy;
import utils.Vector;
import utils.VectorF;

import java.util.List;

public class AimingTower implements Tower {

    private final TowerService towerService;
    private final Vector pos;
    private final Tower baseTower;


    private VectorF angle;


    public AimingTower(TowerService towerService, Vector pos) {
        this.towerService = towerService;
        this.pos = pos;
        this.baseTower = new DefaultTower(towerService, pos);
        this.angle = pos.minus(new VectorF(0, 0));
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

    public void changeAngle(VectorF other) {
        angle = pos.minus(other);
    }

    public VectorF getAngle() {
        return angle;
    }


    public List<? extends Enemy> getEnemiesInRange(float range) {
        return baseTower.getEnemiesInRange(range);
    }

    public float getRange() {
        return 0;
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
