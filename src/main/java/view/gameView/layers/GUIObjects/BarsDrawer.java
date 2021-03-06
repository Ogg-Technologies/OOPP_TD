package view.gameView.layers.GUIObjects;

import model.game.health.Health;
import view.ColorHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian
 * Class for drawing a player healthBar and an enemyHealthBar
 * Is only used in GUIPanel
 */
public class BarsDrawer {

    private static final double BAR_UP = .02;
    private static final double BAR_WIDTH = .01;
    private static final double BAR_HEIGHT = .83;

    private static final double LABEL_HEIGHT = .50;
    private static final double LABEL_UP = BAR_UP + (BAR_HEIGHT - LABEL_HEIGHT) / 2;
    private static final double LABEL_WIDTH = BAR_WIDTH + .40;

    //player health data
    private static final double HEALTH_BAR_LEFT = .145;
    private static final double HEALTH_LABEL_LEFT = HEALTH_BAR_LEFT - .02;

    //Enemy health data
    private static final double ENEMY_ATTACK_HEALTH_BAR_LEFT = 0.845;
    private static final double ENEMY_ATTACK_HEALTH_LABEL_LEFT = ENEMY_ATTACK_HEALTH_BAR_LEFT - .02;

    private final RotatingLabel playerHealthBar;
    private final RotatingLabel enemyHealthBar;

    public BarsDrawer(RotatingLabel playerHealthBar, RotatingLabel enemyHealthBar) {
        this.playerHealthBar = playerHealthBar;
        this.enemyHealthBar = enemyHealthBar;

    }

    /**
     * The actual method that draws the bars
     *
     * @param g            graphics component where everything is drawn
     * @param panelWidth   the whole width of the panel the bars are occupying
     * @param panelHeight  the whole height of the panel the bars are occupying
     * @param playerHealth the healthObject the player has
     * @param enemyHealth  the healthObject the enemies has
     */
    public void draw(Graphics g, int panelWidth, int panelHeight, Health playerHealth, Health enemyHealth) {
        int barY = (int) Math.round(BAR_UP * panelHeight);
        int barWidth = (int) Math.round(BAR_WIDTH * panelWidth);
        int barHeight = (int) Math.round(BAR_HEIGHT * panelHeight);


        int healthBarX = (int) Math.round(HEALTH_BAR_LEFT * panelWidth);
        drawBar(g, healthBarX, barY, barWidth, barHeight, playerHealth.getFraction(),
                ColorHandler.PLAYER_HEALTH, ColorHandler.STANDARD_GUI_BACKGROUND);

        int enemyBarX = (int) Math.round(ENEMY_ATTACK_HEALTH_BAR_LEFT * panelWidth);
        drawBar(g, enemyBarX, barY, barWidth, barHeight, enemyHealth.getFraction(),
                ColorHandler.ENEMY_HEALTH, ColorHandler.STANDARD_GUI_BACKGROUND);


        int labelY = (int) Math.round(LABEL_UP * panelHeight);
        int labelWidth = (int) Math.round(LABEL_WIDTH * panelWidth);
        int labelHeight = (int) Math.round(LABEL_HEIGHT * panelHeight);
        int fontSize = (int) (.07 * panelHeight / 2);


        int healthLabelX = (int) Math.round((HEALTH_LABEL_LEFT) * panelWidth) - labelWidth / 2 + 4 * barWidth + 5;
        updateLabel(playerHealthBar, "" + playerHealth.getCurrent() + " hp", healthLabelX, labelY, labelWidth, labelHeight, fontSize);

        int enemyLabelX = (int) Math.round((ENEMY_ATTACK_HEALTH_LABEL_LEFT) * panelWidth) - labelWidth / 2 + barWidth - 5;
        updateLabel(enemyHealthBar, "" + enemyHealth.getCurrent() + " hp", enemyLabelX, labelY, labelWidth, labelHeight, fontSize);
    }

    private void drawBar(Graphics g, int x, int y, int width, int height, double fraction, Color frontColor, Color backColor) {
        int fractionY = (int) Math.round(y + height * (1 - fraction));
        int fractionHeight = (int) Math.round(height * fraction);
        g.setColor(backColor);
        g.fillRect(x, y, width, height);

        g.setColor(frontColor);
        g.fillRect(x, fractionY, width, fractionHeight);
    }

    private void updateLabel(JLabel label, String text, int x, int y, int width, int height, int fontSize) {
        label.setLocation(x, y);
        label.setSize(width, height);
        label.setText(text);
        label.setFont(new Font("serif", Font.PLAIN, fontSize));
    }
}
