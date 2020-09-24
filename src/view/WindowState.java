package view;

import utils.Vector;

/**
 * A helper class that give view components some data to know where to draw themselves
 */
public class WindowState {
    private int tileSize = 0;
    private Vector offset;


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
        int width = (int) (MAP_WIDTH * totalSize.getX());
        int height = (int) (MAP_HEIGHT * totalSize.getY());

        tileSize = Math.min(width / tileMapSize.getX(), height / tileMapSize.getY());

        int startX = (int) ((MAP_LEFT * totalSize.getX()) + (width - tileSize * tileMapSize.getX()) / 2);
        int startY = (int) ((MAP_UP * totalSize.getY()) + (height - tileSize * tileMapSize.getY()) / 2);

        offset = new Vector(startX, startY);
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

}
