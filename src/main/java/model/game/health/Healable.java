package model.game.health;

/**
 * @Behroz Gives access to players Health without exposing the Mutable Health object
 */
public interface Healable {
    boolean isHealthEqualToMax();

    void addHealth(int amount);
}
