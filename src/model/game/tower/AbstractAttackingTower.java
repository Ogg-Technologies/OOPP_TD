package model.game.tower;

import model.game.enemy.Enemy;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.chargestrategy.ChargeStrategy;
import utils.Vector;
import utils.VectorD;

public abstract class AbstractAttackingTower extends AbstractTower {
    public static final VectorD DEFAULT_AIM = new VectorD(0, 1);
    private final EnemyTargeter enemyTargeter;
    private VectorD aim = DEFAULT_AIM;

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

    private void setAimTo(VectorD targetPos) {
        aim = targetPos.minus(getPos().asVectorD());
    }

    protected abstract void attack(Enemy e);

    public double getAngle() {
        if (aim.getDist() == 0) {
            aim = DEFAULT_AIM;
        }
        return aim.getAngle();
    }
}
