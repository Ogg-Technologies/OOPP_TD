package view.gameLayers;

import application.Constant;
import model.ModelData;
import model.game.map.Tile;
import utils.Vector;
import view.ColorHandler;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sebastian, Samuel, Erik
 * Displays the map.
 * Is used by Swingview.
 */
public class Background extends JPanel { // TODO: Could use some functional decomposition later

    private final ModelData modelData;
    private final WindowState windowState;
    private Vector pos = null;
    private boolean validTile = true;

    private static final BufferedImage baseImage = getBaseImage();

    public Background(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        this.windowState = windowState;
    }

    public void setMousePos(Vector pos, boolean validTile){
        this.pos = pos;
        this.validTile = validTile;
    }

    public void setMousePosToNull(){
        this.pos = null;
    }

    private static BufferedImage getBaseImage() {
        return ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.BASE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ColorHandler.BACKGROUND);
        g.fillRect((int) (WindowState.MAP_LEFT * getWidth()), (int) (WindowState.MAP_UP * getHeight()),
                (int) (WindowState.MAP_WIDTH * getWidth()), (int) (WindowState.MAP_HEIGHT * getHeight()));

        int baseX = -1;
        int baseY = -1;
        Vector mapSize = modelData.getMapSize();
        for (int tileY = 0; tileY < mapSize.getIntY(); tileY++) {
            for (int tileX = 0; tileX < mapSize.getIntX(); tileX++) {

                switch (modelData.getTile(tileX, tileY)) {
                    case START:
                    case PATH:
                        g.setColor(ColorHandler.PATH);
                        break;
                    case GROUND:
                    case BASE:
                        g.setColor(ColorHandler.GROUND);
                        break;
                    default:
                        throw new UnsupportedOperationException("The tile " + modelData.getTile(tileX, tileY)
                                + " has no supported look in View");
                }

                if (modelData.getTile(tileX, tileY) == Tile.BASE) {
                    baseX = tileX;
                    baseY = tileY;
                }

                g.fillRect(tileX * windowState.getTileSize() + windowState.getTileMapOffset().getIntX(),
                        tileY * windowState.getTileSize() + windowState.getTileMapOffset().getIntY(),
                        windowState.getTileSize(), windowState.getTileSize());
            }
        }

        if (pos != null) {
            paintOverlay(pos, g);
        }

        g.drawImage(baseImage, baseX * windowState.getTileSize() + windowState.getTileMapOffset().getIntX(),
                baseY * windowState.getTileSize() + windowState.getTileMapOffset().getIntY(),
                windowState.getTileSize(), windowState.getTileSize(), null);
    }

    private void paintOverlay(Vector tilePos, Graphics g){

        if(tilePos.x >= 0 && tilePos.y >= 0 && tilePos.x < modelData.getMapSize().x && tilePos.y < modelData.getMapSize().y) {
            if (validTile) {
                g.setColor(ColorHandler.VALID_TILE_HOVER);
            } else {
                g.setColor(ColorHandler.INVALID_TILE_HOVER);
            }
            g.fillRect(tilePos.getIntX() * windowState.getTileSize() + windowState.getTileMapOffset().getIntX(),
                    tilePos.getIntY() * windowState.getTileSize() + windowState.getTileMapOffset().getIntY(),
                    windowState.getTileSize(), windowState.getTileSize());
        }
    }

}
