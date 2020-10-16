package view.startLayers;

import model.game.map.TileMap;
import view.ColorHandler;
import view.MapDrawer;
import view.WindowState;

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

    /**
     * The constructor creates all the buttons
     * @param windowState used to change view state
     * @param tileMaps
     */
    public ButtonPanel(WindowState windowState, TileMap[] tileMaps) {
        this.tileMaps = tileMaps;
        startButton = new JButton();
        startButton.addActionListener((e -> windowState.setViewStateToGame()));
        startButton.setText("Start Game");
        add(startButton);

        mapButtons = new JButton[tileMaps.length];
        for(int i = 0; i < mapButtons.length; i++){
            JButton newButton = new JButton();
            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            add(newButton);
            mapButtons[i] = newButton;
            int finalI = i;
            newButton.addActionListener(e -> System.out.println(finalI));
        }
    }

    private void updateButtons(Graphics g) {
        startButton.setSize((int)(getWidth()*0.2),(int)(getHeight()*0.1));
        startButton.setFont(new Font("serif", Font.PLAIN, (int)(getWidth()*0.035)));
        startButton.setLocation((int)(getWidth()*0.4), (int)(getHeight()*0.6));

        int y = (int) (getHeight() * .1);
        int height = (int) (getHeight() * .2);
        int width = (int) (getWidth() * .2);
        int gap = (int) (10 + .1 * width);
        int totalWidth = gap * (mapButtons.length - 1) + width * mapButtons.length;
        int startX = (getWidth() - totalWidth) / 2;
        for(int i = 0; i < mapButtons.length; i++){
            int x = startX + (width + gap) * i;
            MapDrawer.drawMap(g, tileMaps[i], x, y, width, height, ColorHandler.BACKGROUND);
            mapButtons[i].setSize(width, height);
            mapButtons[i].setLocation(x, y);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateButtons(g);
    }
}
