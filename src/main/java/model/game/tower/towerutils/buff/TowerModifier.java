package model.game.tower.towerutils.buff;

/**
 * @author Oskar
 * An interface which describes a way to modify a tower. Used to describe buffs for a tower.
 */
@FunctionalInterface
public interface TowerModifier {
    void modifyTowerMultipliers(TowerMultipliers towerMultipliers);
}
