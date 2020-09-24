package model.game.tower;

import model.game.enemy.Enemy;
import utils.Vector;
import utils.VectorD;

import java.util.List;

public class AimingTower implements Tower {

    private final TowerService towerService;
    private final Vector pos;
    private final Tower baseTower;


    private VectorD angle;


    public AimingTower(TowerService towerService, Vector pos) {
        this.towerService = towerService;
        this.pos = pos;
        this.baseTower = new DefaultTower(towerService, pos);
        this.angle = pos.minus(new VectorD(0, 0));
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

    public void changeAngle(VectorD other) {
        if (angle.getDist() == 0) {
            angle = new VectorD(-1, -1);
        }
        angle = other.minus(pos.asVectorD());
        //TODO bör denna vara public?
    }

    public double getAngle() {
        return angle.getAngle();
    }

    public List<? extends Enemy> getEnemiesInRange(double range) {
        return baseTower.getEnemiesInRange(range);
    }

    public double getRange() {
        return 0;
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }


    public VectorD getAngleVector() {
        return angle;
    }
}
