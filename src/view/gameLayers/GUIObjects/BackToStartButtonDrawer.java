package view.gameLayers.GUIObjects;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian
 * A class for drawing up the backToStart button
 * It is only used in GUIPanel
 */
public class BackToStartButtonDrawer {

    private final double BACK_TO_START_LEFT = .93;
    private final double BACK_TO_START_UP = .02;
    private final double BACK_TO_START_WIDTH = .05;
    private final double BACK_TO_START_HEIGHT = .05;
    private final double BACK_TO_START_FONT = .22;

    private final JButton backToStartButton;

    /**
     * @param backToStartButton the actual button
     */
    public BackToStartButtonDrawer(JButton backToStartButton) {
        this.backToStartButton = backToStartButton;
    }

    /**
     * The actual draw method
     *
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public void draw(int panelWidth, int panelHeight) {
        backToStartButton.setSize((int) (BACK_TO_START_WIDTH * panelWidth), (int) (BACK_TO_START_HEIGHT * panelHeight));
        backToStartButton.setLocation((int) (BACK_TO_START_LEFT * panelWidth), (int) (BACK_TO_START_UP * panelHeight));
        backToStartButton.setFont(new Font("serif", Font.BOLD, (int) (BACK_TO_START_FONT * panelWidth * BACK_TO_START_WIDTH)));
    }

}
