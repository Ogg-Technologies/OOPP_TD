package model.game.tower.concretetowers;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.tower.AimingTower;
import model.game.tower.Tower;
import model.game.tower.TowerService;
import model.game.tower.TowerVisitor;
import utils.Vector;

import java.util.List;
import java.util.Random;

public class GrizzlyBear implements Tower {

    private final static int MAXCHARGE = 88;

    private final AimingTower baseTower;
    private final EventSender eventSender;
    private final double range;
    private int charge;

    public GrizzlyBear(AimingTower baseTower, EventSender eventSender) {
        this.baseTower = baseTower;
        this.eventSender = eventSender;
        this.range = 5;
        this.charge = 87;
    }

    public double getAngle() {
        return baseTower.getAngle();
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
            Enemy currentEnemy = getEnemyToBeAttacked();
            if (currentEnemy != null) {
                attackEnemy(currentEnemy);
                charge = 0;
            } else {
                charge--;
            }
        }
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public List<? extends Enemy> getEnemiesInRange(double range) {
        return baseTower.getEnemiesInRange(range);
    }

    private Enemy getEnemyToBeAttacked() {
        List<? extends Enemy> enemiesInRange = getEnemiesInRange(range);
        if (!enemiesInRange.isEmpty()) {
            Random rand = new Random();
            int currentIndex = rand.nextInt(enemiesInRange.size());
            return enemiesInRange.get(currentIndex);
        }
        return null;
    }

    private void attackEnemy(Enemy currentEnemy) {
        baseTower.changeAngle(currentEnemy.getPos());
        getTowerService().addProjectile(getTowerService().getProjectileFactory().createRock(getPos().asVectorD(),
                baseTower.getAngleVector().setMagnitude(0.3), 1));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }


}
