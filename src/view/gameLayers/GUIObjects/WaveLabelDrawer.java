package view.gameLayers.GUIObjects;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian
 * A class for drawing waveLabel
 * It is only used in GUIPanel
 */
public class WaveLabelDrawer {

    private final double WAVE_LABEL_LEFT = .87;
    private final double WAVE_LABEL_UP = .12;
    private final double WAVE_LABEL_WIDTH = .12;
    private final double WAVE_LABEL_HEIGHT = .2;
    private final double WAVE_LABEL_FONT = .025;

    private final JLabel waveLabel;

    /**
     *
     * @param waveLabel the label to be drawn
     */
    public WaveLabelDrawer(JLabel waveLabel) {
        this.waveLabel = waveLabel;
    }

    /**
     * The actual method that draws the label
     * @param panelWidth the width of the panel
     * @param panelHeight the height of the panel
     * @param waveNumber the current waveNumber to be displayed
     */
    public void draw(int panelWidth, int panelHeight, int waveNumber) {
        waveLabel.setSize((int) (WAVE_LABEL_WIDTH * panelWidth), (int) (WAVE_LABEL_HEIGHT * panelHeight));
        waveLabel.setLocation((int) (WAVE_LABEL_LEFT * panelWidth), (int) (WAVE_LABEL_UP * panelHeight));
        String text = "Wave: " + waveNumber;
        waveLabel.setText(text);
        waveLabel.setFont(new Font("serif", Font.BOLD, (int) (WAVE_LABEL_FONT * panelWidth)));
    }
}
