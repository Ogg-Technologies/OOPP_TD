package controller;

import model.game.tower.Tower;
import view.ControllerStateValue;

/**
 * @author Sebastian, Samuel
 * The state of the controller.
 * Is used by controller.
 */
public class ControllerState implements ControllerStateValue {
    Class<?extends Tower> selectedTower;

    @Override
    public Class<? extends Tower> getSelectedTower() {
        return selectedTower;
    }
}
