package view;

/**
 * @author Sebastian, Samuel
 * An interface for a controller used by the view to add new buttons.
 */
public interface ButtonClickHandler {

    /**
     * Sets the selected tower based on the index of the button which is clicked upon
     * @param index of the clicked button
     */
    void setSelectedTowerIndexButton(int index);

    /**
     * Calls nextWaveClicked in controller when the corresponding button is pressed
     */
    void onNextWaveClicked();
}
