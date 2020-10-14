package model.game.tower.towerutils.buff;

/**
 * @author Oskar
 * A class which combines all active multipliers for a Tower. This class is modified by TowerModifiers to calculate
 * the resulting modifiers from all active Buffs
 */
public class TowerMultipliers {
    private double damageMultiplier = 1;
    private double rangeMultiplier = 1;
    private double fireRateMultiplier = 1;

    public void addDamage(double percentAmount) {
        damageMultiplier += percentAmount / 100;
    }

    public void addRange(double percentAmount) {
        rangeMultiplier += percentAmount / 100;
    }

    public void addFireRate(double percentAmount) {
        fireRateMultiplier += percentAmount / 100;
    }

    public double getDamageMultiplier() {
        return Math.min(damageMultiplier, 3);
    }

    public double getRangeMultiplier() {
        return Math.min(rangeMultiplier, 3);
    }

    public double getFireRateMultiplier() {
        return Math.min(fireRateMultiplier, 3);
    }
}
