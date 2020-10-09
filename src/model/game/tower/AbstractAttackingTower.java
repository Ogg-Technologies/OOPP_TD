package model.game.tower;

import model.game.enemy.Enemy;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.chargestrategy.ChargeStrategy;
import utils.Vector;

/**
 * @author Oskar, Behroz, Erik
 * Common supertype for all towers that attack enemies.
 */
public abstract class AbstractAttackingTower extends AbstractTower {
    public static final Vector DEFAULT_AIM = new Vector(0, 1);
    private final EnemyTargeter enemyTargeter;
    private Vector aim = DEFAULT_AIM;

    /**
     * Creates an attacking tower
     * @param pos The position of the tower
     * @param range The tower's range
     * @param chargeStrategy Strategy on how the tower charges between attacks
     * @param enemyTargeter Helper class used to find an enemy to target
     */
    public AbstractAttackingTower(Vector pos, double range, ChargeStrategy chargeStrategy, EnemyTargeter enemyTargeter) {
        super(pos, range, chargeStrategy);
        this.enemyTargeter = enemyTargeter;
    }

    /**
     * Template method pattern - all attacking towers attacks differently
     * @param e The enemy to attack
     */
    protected abstract void attack(Enemy e);

    /**
     * Attacks an enemy if there is one within its range
     * @return True if it attacked or false if it didn't
     */
    @Override
    protected boolean tryFire() {
        Enemy e = enemyTargeter.getEnemyToAttack(getPos(), getRange());
        if (e == null) {
            return false;
        }
        setAimTo(e.getPos());
        attack(e);
        return true;
    }

    private void setAimTo(Vector targetPos) {
        aim = targetPos.minus(getPos());
    }

    public double getAngle() {
        if (aim.getDist() == 0) {
            aim = DEFAULT_AIM;
        }
        return aim.getAngle();
    }
}
