package view.layers;

import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.SniperBear;
import utils.Vector;
import view.WindowState;
import view.texture.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

/**
 * @author Oskar, Sebastian, Samuel, Erik
 * Display towers.
 * Is used by swingView.
 */

public class TowerDrawer implements TowerVisitor {

    private static final BufferedImage image = getImage();
    private final Graphics graphics;
    private final WindowState windowState;

    public TowerDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }

    private static BufferedImage getImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resource/grizzlyBear.png"));
        } catch (IOException e) {
            throw new IOError(e);
        }
        return image;
    }

    @Override
    public void visit(Tower tower) {

        int x = (int) (windowState.getTileSize() * tower.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * tower.getPos().y + windowState.getTileMapOffset().y);

        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);
    }

    @Override
    public void visit(GrizzlyBear tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage("resource/grizzlyBear.png", tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(BearryPotter tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage("resource/mageBear.png", tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(SniperBear tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage("resource/sniperBear.png", tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    private void drawTower(Image image, Vector pos, double angle) {

        final double sin = Math.abs(Math.sin(angle));
        final double cos = Math.abs(Math.cos(angle));

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int) ((width - windowState.getTileSize()) / 2);

        graphics.drawImage(image, pos.getIntX() - offset, pos.getIntY() - offset, (int) width, (int) width, null);
    }

    private Vector getRealPos(Vector pos) {
        int x = (int) (windowState.getTileSize() * pos.x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * pos.y + windowState.getTileMapOffset().y);
        return new Vector(x, y);
    }

}
