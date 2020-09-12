package view;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import utils.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class EnemyDrawer implements EnemyVisitor {
    private static final BufferedImage image = getImage();
    private final Graphics graphics;
    private final WindowState windowState;

    public EnemyDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }

    private static BufferedImage getImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resource/enemy.png"));
        } catch (IOException e) {
            throw new IOError(e);
        }
        return image;
    }

    @Override
    public void visit(Enemy enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().getX() + windowState.getOffset().getX());
        int y = (int) (windowState.getTileSize() * enemy.getPos().getY() + windowState.getOffset().getY());

        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);
    }
}
