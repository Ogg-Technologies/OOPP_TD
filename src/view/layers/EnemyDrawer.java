package view.layers;

import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import view.WindowState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class EnemyDrawer implements EnemyVisitor {
    private static final BufferedImage image = getEnemyImage();
    private static final double healthHeightPercent = 0.05; //Of tileSize
    private static final double healthWidthPercent = 0.5; //Of tileSize
    private static final Color HEALTH_COLOR = Color.GREEN;   // TODO: Should be the same as player health colors, should probably refactor to Color class
    private static final Color DAMAGE_COLOR = Color.RED;

    private final Graphics graphics;
    private final WindowState windowState;

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

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        //Draws the enemy
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    private void drawHealthBar(Enemy enemy, int x, int y) {
        int healthWidth = (int) (windowState.getTileSize() * enemy.getHealth().getFraction() * healthWidthPercent);
        int healthHeight = (int) (windowState.getTileSize() * healthHeightPercent);
        int healthX = (int) (x + windowState.getTileSize() * ((1 - healthWidthPercent) / 2));
        int healthY = y - healthHeight;

        graphics.setColor(DAMAGE_COLOR);
        graphics.fillRect(healthX, healthY, (int) (windowState.getTileSize() * healthWidthPercent), healthHeight);

        graphics.setColor(HEALTH_COLOR);
        graphics.fillRect(healthX, healthY, healthWidth, healthHeight);
    }
}
