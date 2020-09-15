package view;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class EnemyDrawer implements EnemyVisitor {
    private static final BufferedImage image = getEnemyImage();
    private final Graphics graphics;
    private final WindowState windowState;

    private static final float healthHeightPercent = 0.05f; //Of tileSize
    private static final float healthWidthPercent = 0.5f; //Of tileSize

    public EnemyDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }

    private static BufferedImage getEnemyImage() {
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

        //Draws the enemy
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        //Draws the health bar for the enemy
        int healthWidth = (int) (windowState.getTileSize() * enemy.getHealth().getFraction() * healthWidthPercent);
        int healthHeight = (int) (windowState.getTileSize() * healthHeightPercent);
        int healthX = x + windowState.getTileSize() / 4;
        int healthY = y - healthHeight;
        graphics.setColor(Color.RED);
        graphics.fillRect(healthX, healthY, (int) (windowState.getTileSize() * healthWidthPercent), healthHeight);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(healthX, healthY, healthWidth, healthHeight);

    }
}
