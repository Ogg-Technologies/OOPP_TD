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
    private final int maxVal;

    /**
     * @param controllerState the state of the controller
     * @param maxVal          the max number that is valid as a keyboard input
     */
    public SwingKeyboardController(ControllerState controllerState, int maxVal) {
        this.controllerState = controllerState;
        this.maxVal = maxVal;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * When key is pressed, update controller state if conditions is met.
     * The condition is that the keyboard input must have a "char" value that is not negative, and is less than
     * maxVal. Check @code{Character.getNumericValue(char)} for more information
     *
     * @param e the event data that is gathered
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int index = Character.getNumericValue(e.getKeyChar());
        if (index >= 1 && index <= maxVal) {
            controllerState.setSelectedTowerWithStartIndex(--index);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
