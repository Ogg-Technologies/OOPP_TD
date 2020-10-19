package view.gameView;

import view.*;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Sebastian, Samuel
 * Represent the view with only the necessary methods.
 * Is implemented by swingView and is used by controller and is created by application
 */
public interface IGameView extends Drawable, MouseViewObserver, WindowPositionHelper {
    /**
     * Adds a mouseListener, which takes care about when mouse is clicked
     *
     * @param mouseListener the listener
     */
    void addMouseListener(MouseListener mouseListener);

    /**
     * Adds a mouseMotionListener, which takes care about when mouse is moved
     *
     * @param mouseMotionListener the listener.
     */
    void addMouseMotionListener(MouseMotionListener mouseMotionListener);

    /**
     * Adds a buttonHandler to view
     *
     * @param buttonClickHandler the button handler
     */
    void addButtonClickHandler(ButtonClickHandler buttonClickHandler);

    /**
     * Adds a state from controller to get some data from.
     * Is used in view.
     *
     * @param controllerStateValue the state.
     */
    void addState(ControllerStateValue controllerStateValue);

    /**
     * Adds a keyListener to the window, so that controller can read keyboard inputs
     *
     * @param keyListener the listener
     */
    void addKeyListener(KeyListener keyListener);

    /**
     * A getter for maxTowers in GUIPanel, which describes how many towers can max possibly be in the towerPanel
     * It is used by the keyListener in controller to know which keyboard inputs should be valid or not
     *
     * @return the max number of towers in towerPanel
     */
    int maxTowersInTowerPanel();
}
