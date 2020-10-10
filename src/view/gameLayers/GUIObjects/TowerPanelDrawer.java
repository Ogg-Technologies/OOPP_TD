package view.gameLayers.GUIObjects;

import model.game.tower.Tower;
import view.ColorHandler;
import view.ControllerStateValue;
import view.WindowState;
import view.gameLayers.GUIPanel;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sebastian
 * The drawer for a panel where all the towers can be selected at.
 * It is only used by GUIPanel
 */
public class TowerPanelDrawer {

    private ControllerStateValue controllerStateValue;
    private final JButton[] towerButtons;
    private final JLabel[] towerPriceLabels;

    /**
     * @param towerButtons     the buttons to be updated
     * @param towerPriceLabels the labels to be updated
     */
    public TowerPanelDrawer(JButton[] towerButtons, JLabel[] towerPriceLabels) {
        this.towerButtons = towerButtons;
        this.towerPriceLabels = towerPriceLabels;
    }

    /**
     * A setter for controllerStateValue
     *
     * @param controllerStateValue an interface where information about towers can be fetched
     */
    public void setControllerStateValue(ControllerStateValue controllerStateValue) {
        this.controllerStateValue = controllerStateValue;
    }

    /**
     * The actual draw method for displaying the panel
     *
     * @param g           the graphics component that will be drawn upon
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public void draw(Graphics g, int panelWidth, int panelHeight) {

        if(controllerStateValue == null){
            return;
        }

        int maxTowerButtons = towerButtons.length;

        double percentStartY = (WindowState.MAP_HEIGHT + WindowState.MAP_UP - 0.001);
        double percentStartX = .87 - WindowState.MAP_WIDTH - WindowState.MAP_LEFT;
        int startX = (int) ((WindowState.MAP_LEFT - percentStartX) * panelWidth);
        int startY = (int) (percentStartY * panelHeight);
        int width = (int) ((WindowState.MAP_WIDTH + 2 * percentStartX) * panelWidth);
        int height = (int) ((1 - percentStartY) * panelHeight);
        g.setColor(ColorHandler.TOWER_PANEL);
        g.fillRect(startX, startY, width, height);

        double towerHeightPercent = 0.90;
        double towerWidth = (WindowState.MAP_WIDTH * panelWidth / maxTowerButtons) - 2;
        double towerHeight = height * towerHeightPercent;
        double towerSize = Math.min(towerWidth, towerHeight);
        double towerStartY = (startY + (height - towerSize) / 2);
        double gap = (WindowState.MAP_WIDTH * panelWidth - towerSize * maxTowerButtons) / (maxTowerButtons - 1);
        g.setColor(ColorHandler.TOWER_BUTTON_BACKGROUND);
        Class<? extends Tower>[] towerTypes = controllerStateValue.getAllTowerTypes();
        for (int nr = 0; nr < maxTowerButtons; nr++) {
            //Calculates the xPos for the towerButton
            int towerStartX = (int) (percentStartX * panelWidth + startX + gap * nr + nr * towerSize);
            //Sets the button on the right spot
            towerButtons[nr].setSize((int) (towerSize), (int) (towerSize));
            towerButtons[nr].setLocation(towerStartX, (int) (towerStartY));
            //Adds a background
            g.fillRect(towerStartX, (int) towerStartY, (int) towerSize, (int) towerSize);
            //Paints a tower if there is a sprite for it
            if (nr < GUIPanel.towerPathMap.size() && nr < towerTypes.length) {
                BufferedImage tempImage = ImageHandler.getImage(GUIPanel.towerPathMap.get(towerTypes[nr]), Math.toRadians(90));
                g.drawImage(tempImage, (int) (towerStartX + towerSize * 0.05), (int) (towerStartY + towerSize * 0.05),
                        (int) (towerSize * 0.9), (int) (towerSize * 0.9), null);
                //Populate the label if there is a tower there
                drawPriceLabel(towerStartX, towerStartY, towerSize, nr, controllerStateValue.getTowerPrice(towerTypes[nr]));
            } else {
                drawPriceLabel(towerStartX, towerStartY, towerSize, nr, 0);
            }

        }
    }

    private void drawPriceLabel(int towerStartX, double towerStartY, double towerSize, int index, int price) {
        if (index < GUIPanel.towerPathMap.size()) {
            towerPriceLabels[index].setText("$" + price);
            towerPriceLabels[index].setBackground(ColorHandler.TOWER_BUTTON_LABEL);
        } else {
            towerPriceLabels[index].setText("");
            towerPriceLabels[index].setBackground(ColorHandler.INVISIBLE);

        }
        towerPriceLabels[index].setLocation(towerStartX, (int) (towerStartY + towerSize * 0.8));
        towerPriceLabels[index].setFont(new Font("serif", Font.BOLD, (int) (towerSize * 0.2)));
        towerPriceLabels[index].setSize((int) towerSize, (int) (Math.round(towerSize * 0.2)));
    }
}
