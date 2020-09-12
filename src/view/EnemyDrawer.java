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
    private final Vector offset;
    private final int tileWidth;


    public EnemyDrawer(Graphics graphics, Vector offset, int tileWidth) {
        this.graphics = graphics;
        this.offset = offset;
        this.tileWidth = tileWidth;
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

        int x = (int) (tileWidth * enemy.getPos().getX() + offset.getX());
        int y = (int) (tileWidth * enemy.getPos().getY() + offset.getY());

        graphics.drawImage(image, x, y, tileWidth, tileWidth, null);
    }
}
