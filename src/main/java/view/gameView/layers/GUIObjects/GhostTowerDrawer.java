package view.gameView.layers.GUIObjects;

import model.game.tower.Tower;
import utils.Vector;
import view.ColorHandler;
import view.WindowState;
import view.gameView.layers.GUIPanel;
import view.texture.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sebastian
 * A class for drawing a ghost image of active tower (the tower the user is holding).
 * It is used by GUIPanel.
 */
public class GhostTowerDrawer {

    private final WindowState windowState;

    /**
     * Only gets objects needed to use this class
     *
     * @param windowState a windowState that gives values for tile size
     */
    public GhostTowerDrawer(WindowState windowState) {
        this.windowState = windowState;
    }


    /**
     * Actual method that draws the ghost image
     *
     * @param g              the graphics component to be painted on
     * @param ghostTowerType type of tower for displaying the ghost
     * @param range          of the ghost tower
     * @param mouseTilePos   which tile the mouse hover over
     */
    public void draw(Graphics g, Class<? extends Tower> ghostTowerType, double range, Vector mouseTilePos) {
        if (ghostTowerType == null || mouseTilePos == null) {
            return;
        }

        int tilePosX = mouseTilePos.getIntX() * windowState.getTileSize() + windowState.getTileMapOffset().getIntX();
        int tilePosY = mouseTilePos.getIntY() * windowState.getTileSize() + windowState.getTileMapOffset().getIntY();

        int tileCenterX = tilePosX + windowState.getTileSize() / 2;
        int tileCenterY = tilePosY + windowState.getTileSize() / 2;

        int realRangeRadius = (int) (windowState.getTileSize() * range);

        g.setColor(ColorHandler.GHOST_RANGE);
        g.fillOval(tileCenterX - realRangeRadius, tileCenterY - realRangeRadius,
                realRangeRadius * 2, realRangeRadius * 2);

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

        String path = GUIPanel.towerPathMap.get(ghostTowerType);

        BufferedImage ghostImage = ImageHandler.getImage(path, Math.toRadians(90));
        g.drawImage(ghostImage, tilePosX, tilePosY, windowState.getTileSize(), windowState.getTileSize(), null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
