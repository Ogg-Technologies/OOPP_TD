package controller;

import model.ModelInputListener;
import utils.Vector;
import view.MouseViewObserver;
import view.WindowPositionHelper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A class that trigger events in other classes if a specific mouse action has happened
 */
public class SwingMouseController implements MouseListener, MouseMotionListener {

    private final WindowPositionHelper windowPositionHelper;
    private final MouseViewObserver viewObserver;
    private final ModelInputListener modelInputListener;

    private Vector tilePressed;

    /**
     * @param windowPositionHelper a helper class, that convert a "real" position to a wanted position
     * @param viewObserver         an observer for mouse actions for the view
     * @param modelInputListener   an event handler that activate for some mouse action
     */
    public SwingMouseController(WindowPositionHelper windowPositionHelper, MouseViewObserver viewObserver, ModelInputListener modelInputListener) {
        this.windowPositionHelper = windowPositionHelper;
        this.viewObserver = viewObserver;
        this.modelInputListener = modelInputListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Saves the tile position at which the mouse was pressed down
     */
    @Override
    public void mousePressed(MouseEvent e) {
        tilePressed = getClickedTile(e);
    }

    /**
     * Compares if the mouse release was at the same tile as mouse pressed. If yes, send input to model
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("x: " + (e.getX() - windowPositionHelper.getOffset().x) + ", y: " + (e.getY() - windowPositionHelper.getOffset().y));
        Vector clickedTile = getClickedTile(e);
        if (clickedTile != null && clickedTile.equals(tilePressed)) {
            modelInputListener.onTileClick(clickedTile);
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
}
