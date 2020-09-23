package view;

import utils.Vector;

/**
 * A helper class that give view components some data to know where to draw themselves
 */
public class WindowState {
    private int tileSize = 0;
    private Vector offset;

    /**
     * If screen size is changed, the object needs to be updated, via this method,
     * to give correct data to the view components
     *
     * @param totalSize   the size of the window
     * @param tileMapSize amount of tiles on the map
     */
    public void update(Vector totalSize, Vector tileMapSize) {
        int divider;
        if (totalSize.getX() / tileMapSize.getX() > totalSize.getY() / tileMapSize.getY()) {
            divider = tileMapSize.getY();
            tileSize = totalSize.getY() / divider;
        } else {
            divider = tileMapSize.getX();
            tileSize = totalSize.getX() / divider;
        }

        int mapWidth = tileSize * tileMapSize.getX();
        int mapHeight = tileSize * tileMapSize.getY();

        int startX = (totalSize.getX() - mapWidth) / 2;
        int startY = (totalSize.getY() - mapHeight) / 2;

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
    public Vector getOffset() {
        return offset;
    }
}
