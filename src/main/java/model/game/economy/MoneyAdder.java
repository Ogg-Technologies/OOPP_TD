package model.game.economy;

/**
 * @author Behroz, Erix
 * Interface for towers that increase the players amount of money
 */
@FunctionalInterface
public interface MoneyAdder {
    void addMoney(int amount);
}
