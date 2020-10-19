package view.gameView.layers.GUIObjects;

import view.ColorHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian
 * A drawer for the next wave Button
 * It is only used in GUIPanel
 */
public class NextWaveButtonDrawer {

    private static final double WAVE_BUTTON_LEFT = .87;
    private static final double WAVE_BUTTON_UP = .77;
    private static final double WAVE_BUTTON_WIDTH = .12;
    private static final double WAVE_BUTTON_HEIGHT = .11;

    private final JButton nextWaveButton;

    /**
     * @param nextWaveButton The button to be updated
     */
    public NextWaveButtonDrawer(JButton nextWaveButton) {
        this.nextWaveButton = nextWaveButton;
    }

    /**
     * The actual method that draws the nextWaveButton
     *
     * @param g           the graphics component to be drawn on
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public void draw(Graphics g, int panelWidth, int panelHeight) {
        int startX = (int) (WAVE_BUTTON_LEFT * panelWidth);
        int startY = (int) (WAVE_BUTTON_UP * panelHeight);
        int width = (int) (WAVE_BUTTON_WIDTH * panelWidth);
        int height = (int) (WAVE_BUTTON_HEIGHT * panelHeight);
        nextWaveButton.setSize(width, height);
        nextWaveButton.setLocation(startX, startY);
        g.setColor(ColorHandler.STANDARD_GUI_BACKGROUND);
        g.fillRect(startX, startY, width, height);
        g.setColor(Color.RED);
        double yPercent = (1 - 0.7) / 2;
        int realY1 = (int) (startY + height * yPercent);
        int realY2 = (int) (startY + (1 - yPercent) * height);
        int realY3 = (realY1 + realY2) / 2;

        double xPercent = (1 - .3) / 2;
        int realX12 = (int) (xPercent * width + startX);
        int realX3 = (int) ((1 - xPercent) * width + startX);

        g.fillPolygon(new int[]{realX12, realX12, realX3}, new int[]{realY1, realY2, realY3}, 3);
    }
}
