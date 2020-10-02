package view;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Sebastian, Samuel
 * Represent the view with only the necessary methods.
 * Is implemented by swingView and is used by controller and is created by application
 */
public interface View extends Drawable, MouseViewObserver, ShutDownAble, WindowPositionHelper {
    void start();

    void addMouseListener(MouseListener mouseListener);

    void addMouseMotionListener(MouseMotionListener mouseMotionListener);
}
