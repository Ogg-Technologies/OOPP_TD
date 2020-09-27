package view.layers;

import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.concretetowers.GrizzlyBear;
import utils.Vector;
import view.WindowState;
import view.texture.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;


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

        int x = windowState.getTileSize() * tower.getPos().getX() + windowState.getTileMapOffset().getX();
        int y = windowState.getTileSize() * tower.getPos().getY() + windowState.getTileMapOffset().getY();

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

        graphics.drawImage(rotatedImage, pos.getX() - offset, pos.getY() - offset, (int) width, (int) width, null);
    }

    private Vector getRealPos(Vector pos) {
        int x = windowState.getTileSize() * pos.getX() + windowState.getTileMapOffset().getX();
        int y = windowState.getTileSize() * pos.getY() + windowState.getTileMapOffset().getY();
        return new Vector(x, y);
    }

}
