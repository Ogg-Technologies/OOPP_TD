package view;

import model.game.tower.Tower;

/**
 * @author Sebastian, Samuel
 * Getters for some state values of a controller.
 * Used in view components.
 */
public interface ControllerStateValue {
    /**
     * Getter for selected tower.
     * @return the selected tower.
     */
    Class<? extends Tower> getSelectedTower();
}
