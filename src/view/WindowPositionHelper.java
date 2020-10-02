package view;

import utils.Vector;

/**
 * @author Sebastian, Samuel
 * An interface for components that needs to get info about a position on the window
 * Is implemented by view, and is used by controller
 */
public interface WindowPositionHelper {
    /**
     * @param realPos is the real window pos
     * @return a tile pos or null if there is no tile on this pos
     */
    Vector convertFromRealPosToTilePos(Vector realPos);

    /**
     * In SwingView's window there is an inbuilt offset on all borders of the window
     *
     * @return the offset of what is described above
     */
    Vector getOffset();
}
