package model.game.tower.towerutils.chargestrategy;

/**
 * @author Oskar, Erik
 * Strategy pattern used by towers for how they charge between fires
 */
public interface ChargeStrategy {

    /** Called every time the tower updates */
    void update();

    /**
     * @return True if the strategy is done charging, meaning the tower can fire, false otherwise
     */
    boolean isReadyToFire();

    /**
     * Called when the tower has fired for the charge strategy to adjust accordingly (e.g reset charge)
     */
    void didFire();
}
