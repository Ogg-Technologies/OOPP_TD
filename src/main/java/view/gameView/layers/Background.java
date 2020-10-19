package view.gameView.layers;

import model.ModelData;
import utils.Vector;
import view.ColorHandler;
import view.MapDrawer;
import view.WindowState;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian, Samuel, Erik
 * Displays the map.
 * Is used by Swingview.
 */
public class Background extends JPanel {

    private final ModelData modelData;
    private final WindowState windowState;
    private Vector pos = null;
    private boolean validTile = true;


    /**
     * Gets some constants needed to paint the maps
     *
     * @param modelData   gives information about active map
     * @param windowState gives information about windowSize
     */
    public Background(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        this.windowState = windowState;
    }

    /**
     * Is called by swingView whenever swingView gets a different mousePos
     *
     * @param pos       the pos of the mouse
     * @param validTile if tile is valid or not, this is asked by model from swingView
     */
    public void setMousePos(Vector pos, boolean validTile) {
        this.pos = pos;
        this.validTile = validTile;
    }

    /**
     * This is called by swingView whenever the mouse isn't over a tile
     */
    public void setMousePosToNull() {
        this.pos = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (int) (WindowState.MAP_LEFT * getWidth());
        int y = (int) (WindowState.MAP_UP * getHeight());

        MapDrawer.drawMap(g, modelData.getActiveMap(), x, y,
                windowState.getTotalMapSize().getIntX(), windowState.getTotalMapSize().getIntY(),
                ColorHandler.GAME_BACKGROUND);

        if (pos != null) {
            paintOverlay(pos, g);
        }
    }

    private void paintOverlay(Vector tilePos, Graphics g) {

        if (tilePos.x >= 0 && tilePos.y >= 0 && tilePos.x < modelData.getMapSize().x && tilePos.y < modelData.getMapSize().y) {
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
