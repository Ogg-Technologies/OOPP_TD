package view;

import utils.Vector;

/**
 * @author Oskar, Sebastian, Samuel, Erik
 * A helper class that give view components some data to know where to draw themselves
 * Is used by swingView and all objects in package layers.
 */
public class WindowState {
    private int tileSize = 0;
    private Vector offset;
    private Vector totalMapSize;

    private ViewState viewState = ViewState.START;

    public static final double MAP_LEFT = .17;
    public static final double MAP_UP = .02;
    public static final double MAP_WIDTH = .66;
    public static final double MAP_HEIGHT = .86;

    /**
     * If screen size is changed, the object needs to be updated, via this method,
     * to give correct data to the view components
     *
     * @param totalSize   the size of the window
     * @param tileMapSize amount of tiles on the map
     */
    public void update(Vector totalSize, Vector tileMapSize) {
        int width = (int) (MAP_WIDTH * totalSize.x);
        int height = (int) (MAP_HEIGHT * totalSize.y);

        tileSize = (int) Math.min(width / tileMapSize.x, height / tileMapSize.y);

        int startX = (int) ((MAP_LEFT * totalSize.x) + (width - tileSize * tileMapSize.x) / 2);
        int startY = (int) ((MAP_UP * totalSize.y) + (height - tileSize * tileMapSize.y) / 2);

        offset = new Vector(startX, startY);
        totalMapSize = new Vector(width, height);
    }

    /**
     * @return the size of each tile (all tiles are squares)
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * @return an offset, because the tilemap is shown in the middle of the window,
     * there will be some empty spaces (this offset).
     */
    public Vector getTileMapOffset() {
        return offset;
    }

    /**
     * Getter for total size of the map, including background
     *
     * @return total size as a Vector
     */
    public Vector getTotalMapSize() {
        return totalMapSize;
    }

    /**
     * Getter for which view state is active
     *
     * @return the view state
     */
    public ViewState getViewState() {
        return viewState;
    }

    /**
     * Used for when changing to startViewState
     */
    public void setViewStateToStart() {
        this.viewState = ViewState.START;
    }

    /**
     * Used for when changing to gameViewState
     */
    public void setViewStateToGame() {
        this.viewState = ViewState.GAME;
    }
}
