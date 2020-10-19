package view;

import utils.Vector;

/**
 * @author Sebastian, Samuel
 * Interface that updates mousePositon from controller.
 * Is used by view
 */
public interface MouseViewObserver {
    void updateMousePosition(Vector v);
}
