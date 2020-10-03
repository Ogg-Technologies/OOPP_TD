package view;

import model.ModelInputListener;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Sebastian, Samuel
 * Represent the view with only the necessary methods.
 * Is implemented by swingView and is used by controller and is created by application
 */
public interface View extends Drawable, MouseViewObserver, ShutDownAble, WindowPositionHelper {
    /**
     * This is supposed to be called soon after the constructor call
     */
    void start();

    /**
     * Adds a mouseListener, which takes care about when mouse is clicked
     * @param mouseListener the listener
     */
    void addMouseListener(MouseListener mouseListener);

    /**
     * Adds a mouseMotionListener, which takes care about when mouse is moved
     * @param mouseMotionListener the listener.
     */
    void addMouseMotionListener(MouseMotionListener mouseMotionListener);

    /**
     * Adds a buttonHandler and an object that takes care of all the methods calls from these buttons
     * @param buttonClickHandler the button handler
     * @param modelInputListener the method handler
     */
    void addButtonAndMethodHandler(ButtonClickHandler buttonClickHandler, ModelInputListener modelInputListener);
}
