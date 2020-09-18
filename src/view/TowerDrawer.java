package view;

import model.game.tower.AimingTower;
import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.concretetowers.GrizzlyBear;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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

        int x = (int) (windowState.getTileSize() * tower.getPos().getX() + windowState.getOffset().getX());
        int y = (int) (windowState.getTileSize() * tower.getPos().getY() + windowState.getOffset().getY());

        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);
    }

    @Override
    public void visit(GrizzlyBear tower) {

        int x = (int) (windowState.getTileSize() * tower.getPos().getX() + windowState.getOffset().getX());
        int y = (int) (windowState.getTileSize() * tower.getPos().getY() + windowState.getOffset().getY());

        final double sin = Math.abs(Math.sin(tower.getAngle()));
        final double cos = Math.abs(Math.cos(tower.getAngle()));

        BufferedImage tempImage = rotate(image, tower.getAngle(), sin, cos);

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int)((width-windowState.getTileSize())/2);

        graphics.drawImage(tempImage, x-offset, y-offset, (int)width, (int)width, null);
    }

    public static BufferedImage rotate(BufferedImage image, double angle, double sin, double cos) {
        final int width = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int height = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(width, height, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(width / 2, height / 2);
        at.rotate(angle,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        return rotatedImage;
    }

}
