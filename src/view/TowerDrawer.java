package view;

import model.game.tower.Tower;
import model.game.tower.TowerVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
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
            image = ImageIO.read(new File("resource/bearTower.png"));
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

}
