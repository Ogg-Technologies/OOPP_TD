package view.gameLayers.GUIObjects;

import view.ColorHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian
 * <p>
 * A drawer for money label
 * Only used in GUIPanel
 */
public class MoneyLabelDrawer {
    private static final double MONEY_LEFT = .01;
    private static final double MONEY_UP = .02;
    private static final double MONEY_WIDTH = .12;
    private static final double MONEY_HEIGHT = .06;

    private final JLabel moneyLabel;

    /**
     * @param moneyLabel the label to be updated
     */
    public MoneyLabelDrawer(JLabel moneyLabel) {
        this.moneyLabel = moneyLabel;
    }

    /**
     * The actual method that draws the moneyLabel
     *
     * @param g           the graphics component to draw upon
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     * @param money       the money amount to update label with
     */
    public void draw(Graphics g, int panelWidth, int panelHeight, int money) {
        int x = (int) (MONEY_LEFT * panelWidth);
        int y = (int) (MONEY_UP * panelHeight);
        int width = (int) (MONEY_WIDTH * panelWidth);
        int height = (int) (MONEY_HEIGHT * panelHeight);
        int fontSize = (int) (width / 4.2); //.2 because some sort of margin
        g.setColor(ColorHandler.STANDARD_GUI_BACKGROUND);
        g.fillRect(x, y, width, height);
        moneyLabel.setLocation(x, y);
        moneyLabel.setSize(width, height);
        moneyLabel.setText("$ " + money);
        moneyLabel.setFont(new Font("serif", Font.BOLD, fontSize));
    }
}
