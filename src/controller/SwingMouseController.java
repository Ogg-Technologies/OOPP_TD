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
        System.out.println("x: " + (e.getX() - windowPositionHelper.getOffset().x) + ", y: " + (e.getY() - windowPositionHelper.getOffset().y));
        Vector clickedTile = windowPositionHelper.convertFromRealPosToTilePos(new Vector(
                e.getX() - windowPositionHelper.getOffset().x,
                e.getY() - windowPositionHelper.getOffset().y)
        );
        if (clickedTile != null) {
            modelInputListener.onTileClick(clickedTile);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vector pos = new Vector(e.getX() - windowPositionHelper.getOffset().x, e.getY() - windowPositionHelper.getOffset().y);
        viewObserver.updateMousePosition(pos);
    }
}
