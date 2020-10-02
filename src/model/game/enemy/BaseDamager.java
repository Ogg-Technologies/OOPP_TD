package model.game.enemy;

/**
 * @author Oskar, Erik
 * An interface which is used by enemies to damage the base when they reach it
 */
@FunctionalInterface
public interface BaseDamager {

    void damageBase(int amount);
}