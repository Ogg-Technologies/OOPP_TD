package view;

import utils.Vector;

public class WindowState {
    private int tileSize = 0;
    private Vector offset;

    public void update(Vector totalSize, Vector tileMapSize){
        int divider;
        if(totalSize.getX()/tileMapSize.getX() > totalSize.getY()/ tileMapSize.getY()) {
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

    public int getTileSize() {
        return tileSize;
    }

    public Vector getOffset() {
        return offset;
    }
}
