package model;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * An interface which allows ApplicationLoop to update the Model without exposing other functionality
 */
@FunctionalInterface
public interface Updatable {
    void update();
}
