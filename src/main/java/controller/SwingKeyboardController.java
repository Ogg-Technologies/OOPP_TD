package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Sebastian
 * Handles keyboard inputs and delegate them to controller state
 * It is used as a Keylistener in SwingView and is created in Controller
 */
public class SwingKeyboardController implements KeyListener {

    private final ControllerState controllerState;
    private final int numberOfTowersInPanel;

    /**
     * @param controllerState       the state of the controller
     * @param numberOfTowersInPanel the max number that is valid as a keyboard input
     */
    public SwingKeyboardController(ControllerState controllerState, int numberOfTowersInPanel) {
        this.controllerState = controllerState;
        this.numberOfTowersInPanel = numberOfTowersInPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * When key is pressed, update controller state if conditions is met.
     * The condition is that the keyboard input must have a "char" value that is not negative, and is less than
     * maxVal. Check @code{Character.getNumericValue(char)} for more information
     * It also listen to left and right arrow keys for changing start index in controller state.
     *
     * @param e the event data that is gathered
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int index = Character.getNumericValue(e.getKeyChar());
        if (index >= 1 && index <= numberOfTowersInPanel) {
            controllerState.setSelectedTowerWithStartIndex(--index);
        }
        //Left arrow key
        if (e.getKeyCode() == 37) {
            controllerState.changeStartIndex(-numberOfTowersInPanel);
        }
        //Right arrow key
        if (e.getKeyCode() == 39) {
            controllerState.changeStartIndex(numberOfTowersInPanel);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
