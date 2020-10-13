package model.game.tower.towerutils.chargestrategy;

/**
 * @author Oskar, Erik
 * Charge strategy for a constant interval between fires
 */
public class ConstantChargeStrategy implements ChargeStrategy {
    private final int maxCharge;
    private double charge = 0;

    /**
     * @param maxCharge the number of updates between fires
     */
    public ConstantChargeStrategy(int maxCharge) {
        this.maxCharge = maxCharge;
    }

    @Override
    public void update() {
        update(1);
    }

    @Override
    public void update(double fireRateMultiplier) {
        if (charge < maxCharge) {
            charge += fireRateMultiplier;
        }
    }

    @Override
    public boolean isReadyToFire() {
        return charge >= maxCharge;
    }

    @Override
    public void notifyFirePerformed() {
        charge = 0;
    }
}
