package model.game.tower.towerutils.chargestrategy;

/**
 * @author Behroz
 * Charge strategy whith reguler bursts of attack and the pauses for set ammount of ticks
 */
public class BurstChargeStrategy implements ChargeStrategy {
    private final int maxCharge;
    private final int attacksPerCharge;
    private int attacksLeft;

    private int charge = 0;

    /**
     * @param maxCharge        the number of updates between bursts
     * @param attacksPerCharge the number of attacks in one burst
     */
    public BurstChargeStrategy(int maxCharge, int attacksPerCharge) {
        this.maxCharge = maxCharge;
        this.attacksPerCharge = attacksPerCharge;
        this.attacksLeft = attacksPerCharge;
    }

    @Override
    public void update() {
        if (charge < maxCharge) {
            charge++;
        }
    }

    @Override
    public boolean isReadyToFire() {
        return charge >= maxCharge;
    }

    @Override
    public void didFire() {
        attacksLeft--;
        if (attacksLeft == 0) {
            attacksLeft = attacksPerCharge;
            charge = 0;
        }
    }
}

