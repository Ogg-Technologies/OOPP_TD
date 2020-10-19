package view;

import config.Config;
import model.game.map.Tile;
import model.game.map.TileMap;
import utils.Vector;
import view.texture.ImageHandler;

import java.awt.*;

/**
 * @author Sebastian
 * A class only created for having same functionality in the same place
 * It is used by gameLayers.Background and startLayers.ButtonPanel for drawing maps
 */
public class MapDrawer {
    public static void drawMap(Graphics g, TileMap tileMap, int x, int y, int totalWidth, int totalHeight, Color background) {
        if (tileMap == null) {
            return;
        }

        g.setColor(background);
        g.fillRect(x, y, totalWidth, totalHeight);

        int baseX = -1;
        int baseY = -1;
        Vector mapSize = tileMap.getSize();

        int tileWidth = totalWidth / mapSize.getIntX();
        int tileHeight = totalHeight / mapSize.getIntY();

        int tileSize = Math.min(tileWidth, tileHeight);

        if (tileSize * mapSize.getIntX() < totalWidth) {
            x += (totalWidth - tileSize * mapSize.getIntX()) / 2;
        }

        if (tileSize * mapSize.getIntY() < totalHeight) {
            y += (totalHeight - tileSize * mapSize.getIntY()) / 2;
        }

        for (int tileY = 0; tileY < mapSize.getIntY(); tileY++) {
            for (int tileX = 0; tileX < mapSize.getIntX(); tileX++) {

                g.setColor(colorOfTileType(tileMap.getTile(tileX, tileY)));

                if (tileMap.getTile(tileX, tileY) == Tile.BASE) {
                    baseX = tileX;
                    baseY = tileY;
                }

                g.fillRect(tileX * tileSize + x, tileY * tileSize + y, tileSize, tileSize);
            }
        }

        g.drawImage(ImageHandler.getImage(Config.INSTANCE.IMAGE_PATH.BASE), baseX * tileSize + x,
                baseY * tileSize + y, tileSize, tileSize, null);
    }

    private static Color colorOfTileType(Tile tile) {
        switch (tile) {
            case START:
            case PATH:
                return ColorHandler.PATH;
            case GROUND:
            case BASE:
                return ColorHandler.GROUND;
            default:
                throw new UnsupportedOperationException("The tile " + tile
                        + " has no supported look in View");
        }
    }
}
