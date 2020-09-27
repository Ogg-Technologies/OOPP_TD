package model.game.tower;

import model.game.enemy.Enemy;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.chargestrategy.ChargeStrategy;
import utils.Vector;

public abstract class AbstractAttackingTower extends AbstractTower {
    public static final Vector DEFAULT_AIM = new Vector(0, 1);
    private final EnemyTargeter enemyTargeter;
    private Vector aim = DEFAULT_AIM;

    public AbstractAttackingTower(Vector pos, double range, ChargeStrategy chargeStrategy, EnemyTargeter enemyTargeter) {
        super(pos, range, chargeStrategy);
        this.enemyTargeter = enemyTargeter;
    }

    @Override
    protected boolean tryFire() {
        Enemy e = enemyTargeter.getEnemyToAttack(getPos(), getRange());
        if (e == null) {
            return false;
        }
        attack(e);
        setAimTo(e.getPos());
        return true;
    }

    private void setAimTo(Vector targetPos) {
        aim = targetPos.minus(getPos());
    }

    protected abstract void attack(Enemy e);

    public double getAngle() {
        if (aim.getDist() == 0) {
            aim = DEFAULT_AIM;
        }
        return aim.getAngle();
    }
}
