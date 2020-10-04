package view;

import model.game.tower.Tower;

/**
 * @author Sebastian
 * An interface for a controller used by the view to add new buttons.
 */
public interface ButtonClickHandler {
    /**
     * Sets the selected tower as the tower class from the corresponding button.
     * @param towerClass the tower to be selected.
     */
    void setSelectedTower(Class<? extends Tower> towerClass);

    void onNextWaveClicked();
}
