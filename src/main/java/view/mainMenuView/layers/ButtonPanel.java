package view.mainMenuView.layers;

import model.game.map.TileMap;
import view.ColorHandler;
import view.MapDrawer;
import view.OnCloseWithIndex;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian, Samuel
 * Panel for start window, where all buttons are
 * It is used by view
 */
public class ButtonPanel extends JPanel {
    private final JButton startButton;
    private final TileMap[] tileMaps;
    private final JButton[] mapButtons;
    private final JLabel[] mapNameLabels;
    private int selectedMapIndex = -1;

    /**
     * The constructor creates all the buttons
     *
     * @param onCloseWithIndex what happens when this view should close
     * @param tileMaps         all the maps that has been loaded in
     */
    public ButtonPanel(OnCloseWithIndex onCloseWithIndex, TileMap[] tileMaps) {
        this.tileMaps = tileMaps;
        startButton = new JButton();
        startButton.addActionListener((e -> onCloseWithIndex.close(selectedMapIndex)));
        startButton.setText("Start Game");
        add(startButton);

        mapButtons = new JButton[tileMaps.length];
        mapNameLabels = new JLabel[tileMaps.length];
        for (int i = 0; i < mapButtons.length; i++) {
            JButton newButton = new JButton();
            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            int finalI1 = i;
            newButton.addActionListener(e -> selectedMapIndex = finalI1);
            add(newButton);
            mapButtons[i] = newButton;
            JLabel newLabel = new JLabel(tileMaps[i].getName());
            newLabel.setBackground(ColorHandler.STANDARD_GUI_BACKGROUND);
            newLabel.setOpaque(true);
            newLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(newLabel);
            mapNameLabels[i] = newLabel;
        }
    }

    private void updateButtons(Graphics g) {
        int buttonWidth = (int) (getWidth() * .2);
        startButton.setSize(buttonWidth, (int) (getHeight() * 0.1));
        startButton.setFont(new Font("serif", Font.PLAIN, buttonWidth / 8));
        startButton.setLocation((int) (getWidth() * 0.4), (int) (getHeight() * 0.7));

        paintMaps(g);
    }


    private void paintMaps(Graphics g) {

        int width = (int) (getWidth() * .2);
        int height = (int) (getHeight() * .2);
        int gap = (int) (10 + .1 * width);
        int totalWidth = gap * (mapButtons.length - 1) + width * mapButtons.length;

        //Calculates how many upper and lower map buttons there are
        //and also total size of them
        int upperWidth = totalWidth;
        int lowerWidth = -1;
        int numberOfLower = -1;
        if (totalWidth > getWidth()) {
            int temp = totalWidth - gap - width;
            numberOfLower = 1;
            while (temp > getWidth()) {
                numberOfLower++;
                temp -= gap + width;
            }
            upperWidth = temp;
            lowerWidth = totalWidth - temp - gap;
        }

        int y = (int) (getHeight() * .1);
        int startX = (getWidth() - upperWidth) / 2;
        for (int i = 0; i < mapButtons.length; i++) {
            int index = i;
            if (i == mapButtons.length - numberOfLower) {
                startX = (getWidth() - lowerWidth) / 2;
                y += height + (int) (.1 * getHeight());
            }
            if (i >= mapButtons.length - numberOfLower && numberOfLower != -1) {
                index = i - mapButtons.length + numberOfLower;
            }

            int x = startX + (width + gap) * index;

            if (selectedMapIndex == i) {
                g.setColor(ColorHandler.CLICKED_BUTTON_BORDER);
                g.fillRect(x - 2, y - 2, width + 4, height + 4);
            }

            MapDrawer.drawMap(g, tileMaps[i], x, y, width, height, ColorHandler.BACKGROUND);
            mapButtons[i].setSize(width, height);
            mapButtons[i].setLocation(x, y);

            int labelHeight = (int) (.05 * getHeight());
            Font labelFont = new Font("serif", Font.PLAIN, width / 13);
            mapNameLabels[i].setFont(labelFont);
            mapNameLabels[i].setSize(width, labelHeight);
            mapNameLabels[i].setLocation(x, height + y + (int) (.02 * getHeight()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateButtons(g);
    }
}
