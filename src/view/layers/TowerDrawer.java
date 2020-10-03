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

        final double sin = Math.abs(Math.sin(tower.getAngle()));
        final double cos = Math.abs(Math.cos(tower.getAngle()));

        BufferedImage rotatedImage = ImageHandler.getImage("resource/grizzlyBear.png", tower.getAngle());

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int) ((width - windowState.getTileSize()) / 2);

        graphics.drawImage(rotatedImage, pos.getIntX() - offset, pos.getIntY() - offset, (int) width, (int) width, null);
    }

    @Override
    public void visit(BearryPotter tower) {
        // TODO: Refactor this so that it is not just copy paste of GrizzlyBear tower

        Vector pos = getRealPos(tower.getPos());

        final double sin = Math.abs(Math.sin(tower.getAngle()));
        final double cos = Math.abs(Math.cos(tower.getAngle()));

        BufferedImage rotatedImage = ImageHandler.getImage("resource/mageBear.png", tower.getAngle());

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int) ((width - windowState.getTileSize()) / 2);

        graphics.drawImage(rotatedImage, pos.getIntX() - offset, pos.getIntY() - offset, (int) width, (int) width, null);
    }

    @Override
    public void visit(SniperBear tower) {
        // TODO: Refactor this so that it is not just copy paste of GrizzlyBear tower

        Vector pos = getRealPos(tower.getPos());

        final double sin = Math.abs(Math.sin(tower.getAngle()));
        final double cos = Math.abs(Math.cos(tower.getAngle()));

        BufferedImage rotatedImage = ImageHandler.getImage("resource/sniperBear.png", tower.getAngle());

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int) ((width - windowState.getTileSize()) / 2);

        graphics.drawImage(rotatedImage, pos.getIntX() - offset, pos.getIntY() - offset, (int) width, (int) width, null);
    }

    private Vector getRealPos(Vector pos) {
        int x = (int) (windowState.getTileSize() * pos.x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * pos.y + windowState.getTileMapOffset().y);
        return new Vector(x, y);
    }

}
