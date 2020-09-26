package model.game.tower;

public interface ChargeStrategy {
    void update();

    boolean isReadyToFire();

    void didFire();
}
