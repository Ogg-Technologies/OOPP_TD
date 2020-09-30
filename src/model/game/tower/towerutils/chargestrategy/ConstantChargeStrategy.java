package model.game.tower.towerutils.chargestrategy;

/**
 * Charge strategy for a constant interval between fires
 */
public class ConstantChargeStrategy implements ChargeStrategy {
    private final int maxCharge;
    private int charge = 0;

    /**
     * @param maxCharge the number of updates between fires
     */
    public ConstantChargeStrategy(int maxCharge) {
        this.maxCharge = maxCharge;
    }

    @Override
    public void update() {
        if (charge < maxCharge) {
            charge++;
        }
    }

    @Override
    public boolean isReadyToFire() {
        return charge == maxCharge;
    }

    @Override
    public void didFire() {
        charge = 0;
    }
}
