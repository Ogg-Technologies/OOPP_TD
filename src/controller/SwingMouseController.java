package controller;

import model.ModelInputListener;
import utils.Vector;
import view.ButtonClickHandler;
import view.MouseViewObserver;
import view.WindowPositionHelper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Sebastian, Samuel, Erik
 * A class that trigger events in other classes if a specific mouse action has happened
 * This is used by Controller and View
 */
public class SwingMouseController implements MouseListener, MouseMotionListener, ButtonClickHandler {

    private final WindowPositionHelper windowPositionHelper;
    private final MouseViewObserver viewObserver;
    private final ModelInputListener modelInputListener;


    private final ControllerState controllerState;
    private Vector tilePressed;

    /**
     * @param windowPositionHelper a helper class, that convert a "real" position to a wanted position
     * @param viewObserver         an observer for mouse actions for the view
     * @param modelInputListener   an event handler that activate for some mouse action
     */
    public SwingMouseController(WindowPositionHelper windowPositionHelper, MouseViewObserver viewObserver,
                                ModelInputListener modelInputListener, ControllerState controllerState) {
        this.windowPositionHelper = windowPositionHelper;
        this.viewObserver = viewObserver;
        this.modelInputListener = modelInputListener;
        this.controllerState = controllerState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Saves some data when mouse is pressed, for when it is released.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        tilePressed = getClickedTile(e);
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            onLeftClick(e);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            onRightClick();
        }
    }

    /**
     * Compares if the mouse release was over the same object as when mouse was pressed.
     * If yes, send a method call to model.
     */
    private void onLeftClick(MouseEvent e) {
        Vector clickedTile = getClickedTile(e);
        if (clickedTile != null && clickedTile.equals(tilePressed)) {
            if (controllerState.selectedTower != null) {
                modelInputListener.onTileClick(controllerState.selectedTower.getTowerFactory(), clickedTile, controllerState.selectedTower.getTowerType());
            }
        }
        controllerState.selectedTower = null;
    }

    /**
     * deselects tower in controllerState if one is selected.
     */
    private void onRightClick() {
        if (controllerState.selectedTower != null) {
            controllerState.selectedTower = null;
        }
    }

    /**
     * Given a MouseEvent it calculates which tile was clicked and returns it, or null if none was
     *
     * @param e The MouseEvent created by Swing
     * @return The tile position clicked
     */
    private Vector getClickedTile(MouseEvent e) {
        return windowPositionHelper.convertFromRealPosToTilePos(
                new Vector(
                        e.getX() - windowPositionHelper.getOffset().x,
                        e.getY() - windowPositionHelper.getOffset().y
                )
        );
    }

    /**
     * Sends the mouse position from the MouseEvent to View to make View update its displaying of mouse position
     *
     * @param e The MouseEvent that triggered the call
     */
    private void updateViewMousePosition(MouseEvent e) {
        Vector pos = new Vector(e.getX() - windowPositionHelper.getOffset().x, e.getY() - windowPositionHelper.getOffset().y);
        viewObserver.updateMousePosition(pos);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateViewMousePosition(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateViewMousePosition(e);
    }

    @Override
    public void setSelectedTowerIndexButton(int index) {
        controllerState.setSelectedTowerWithIndex(index);
    }

    @Override
    public void onNextWaveClicked() {
        modelInputListener.onStartNewWave();
    }

}
