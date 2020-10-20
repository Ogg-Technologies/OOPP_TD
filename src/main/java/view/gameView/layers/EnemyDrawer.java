package view.gameView.layers;

import config.Config;
import model.game.enemy.AbstractEnemy;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;
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
    private static final double healthHeightPercent = 0.05; //Of tileSize
    private static final double healthWidthPercent = 0.5; //Of tileSize

    private final Graphics graphics;
    private final WindowState windowState;

    /**
     * @param g           graphics component where the image will be drawn upon
     * @param windowState gives the "real" tile size for deciding what size the enemy should be.
     */
    public EnemyDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }


    @Override
    public void visit(Enemy enemy) {
        throw new IllegalArgumentException("This enemy " + enemy.getClass().getSimpleName() + " is not implemented in EnemyDrawer");
    }

    @Override
    public void visit(BasicEnemy.Fishstick enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.FISHSTICK);
    }

    @Override
    public void visit(BasicEnemy.Swordfish enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.SWORDFISH);
    }

    @Override
    public void visit(BasicEnemy.FishAndChips enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.FISH_AND_CHIPS);
    }

    @Override
    public void visit(BasicEnemy.FishInABoat enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.FISH_IN_A_BOAT);
    }

    @Override
    public void visit(BasicEnemy.Sailfish enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.SAILFISH);
    }

    @Override
    public void visit(BasicEnemy.Shark enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.SHARK);
    }

    @Override
    public void visit(BasicEnemy.FishInAFishTank enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.FISH_IN_A_FISH_TANK);
    }

    @Override
    public void visit(BasicEnemy.TankSinatra enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.TANK_SINATRA);
    }

    @Override
    public void visit(FlyingFish enemy) {
        drawEnemy(enemy, Config.INSTANCE.IMAGE_PATH.FLYING_FISH);
    }

    private void drawEnemy(AbstractEnemy enemy, String path) {
        int x = (int) (windowState.getTileSize() * enemy.getPos().x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * enemy.getPos().y + windowState.getTileMapOffset().y);

        BufferedImage image = ImageHandler.getImage(path);

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
