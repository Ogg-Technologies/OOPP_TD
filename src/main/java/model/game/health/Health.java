package model.game.health;

/**
 * @author Oskar
 * Immutable interface of MutableHealth
 * Used by the view to display health of entities in the game
 */
public interface Health {
    int getCurrent();

    int getMax();

    double getFraction();

    boolean isDead();

    void setZero();
}
