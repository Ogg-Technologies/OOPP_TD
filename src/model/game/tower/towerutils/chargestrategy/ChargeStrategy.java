package model.game.tower.towerutils.chargestrategy;

public interface ChargeStrategy {
    void update();

    boolean isReadyToFire();

    void didFire();
}
