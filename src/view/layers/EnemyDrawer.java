package view.layers;

import application.Constant;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.concreteenemies.BasicEnemy;
import view.ColorHandler;
import view.WindowState;
import view.texture.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Oskar, Sebastian, Samuel, Erik
 * Displays all the Enemies.
 * Is used by swingView.
 */

public class EnemyDrawer implements EnemyVisitor {
    private static final BufferedImage image = getEnemyImage();
    private static final double healthHeightPercent = 0.05; //Of tileSize
    private static final double healthWidthPercent = 0.5; //Of tileSize

    private final Graphics graphics;
    private final WindowState windowState;

    public EnemyDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }

    private static BufferedImage getEnemyImage() {
        return ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.FISHSTICK);
    }

    @Override
    public void visit(Enemy enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        //Draws the enemy
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.Swordfish enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/swordfish.png");

        //Draws the Swordfish
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.FishAndChips enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/fishAndChips.png");

        //Draws the FishAndChips
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.FishInABoat enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/fishInABoat.png");

        //Draws the FishInABoat
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.Sailfish enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/sailfish.png");

        //Draws the Sailfish
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.Shark enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/shark.png");

        //Draws the Shark
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    @Override
    public void visit(BasicEnemy.FishInAFishTank enemy) {

        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage("resource/fishInAFishTank.png");

        //Draws the FishInAFishTank
        graphics.drawImage(image, x, y, windowState.getTileSize(), windowState.getTileSize(), null);

        drawHealthBar(enemy, x, y);
    }

    private void drawHealthBar(Enemy enemy, int x, int y) {
        int healthWidth = (int) (windowState.getTileSize() * enemy.getHealth().getFraction() * healthWidthPercent);
        int healthHeight = (int) (windowState.getTileSize() * healthHeightPercent);
        int healthX = (int) (x + windowState.getTileSize() * ((1 - healthWidthPercent) / 2));
        int healthY = y - healthHeight;

        graphics.setColor(ColorHandler.ENEMY_HEALTH);
        graphics.fillRect(healthX, healthY, healthWidth, healthHeight);
    }
}
