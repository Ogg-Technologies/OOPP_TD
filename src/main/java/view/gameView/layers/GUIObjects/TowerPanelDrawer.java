package view.gameView.layers.GUIObjects;

import config.Config;
import model.game.tower.Tower;
import view.ColorHandler;
import view.ControllerStateValues;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author Sebastian
 * The drawer for a panel where all the towers can be selected at.
 * It is only used by GUIPanel
 */
public class TowerPanelDrawer {

    private ControllerStateValues controllerStateValues;
    private final JButton[] towerButtons;
    private final JLabel[] towerPriceLabels;
    private final JButton[] arrowButtons;
    private Map<Class<? extends Tower>, String> towerImagePathMap;

    /**
     * @param towerButtons     the buttons to be updated
     * @param towerPriceLabels the labels to be updated
     * @param arrowButtons     the buttons for the arrows on the panel
     */
    public TowerPanelDrawer(JButton[] towerButtons, JLabel[] towerPriceLabels, JButton[] arrowButtons, Map<Class<? extends Tower>, String> towerImagePathMap) {
        this.towerButtons = towerButtons;
        this.towerPriceLabels = towerPriceLabels;
        this.arrowButtons = arrowButtons;
        this.towerImagePathMap = towerImagePathMap;
    }

    /**
     * A setter for controllerStateValues
     *
     * @param controllerStateValues an interface where information about towers can be fetched
     */
    public void setControllerStateValues(ControllerStateValues controllerStateValues) {
        this.controllerStateValues = controllerStateValues;
    }

    private static final double PERCENT_START_X = .87 - WindowState.MAP_WIDTH - WindowState.MAP_LEFT;
    private static final double PERCENT_START_Y = WindowState.MAP_HEIGHT + WindowState.MAP_UP - .001;

    /**
     * The actual draw method for displaying the panel
     *
     * @param g           the graphics component that will be drawn upon
     * @param panelWidth  the width of the panel
     * @param panelHeight the height of the panel
     */
    public void draw(Graphics g, int panelWidth, int panelHeight) {

        if (controllerStateValues == null) {
            return;
        }

        int maxTowerButtons = towerButtons.length;

        int startX = (int) ((WindowState.MAP_LEFT - PERCENT_START_X) * panelWidth);
        int startY = (int) (PERCENT_START_Y * panelHeight);
        int width = (int) ((WindowState.MAP_WIDTH + 2 * PERCENT_START_X) * panelWidth);
        int height = (int) ((1 - PERCENT_START_Y) * panelHeight);
        g.setColor(ColorHandler.TOWER_PANEL);
        g.fillRect(startX, startY, width, height);

        double towerHeightPercent = 0.90;
        double towerWidth = (WindowState.MAP_WIDTH * panelWidth / maxTowerButtons) - 2;
        double towerHeight = height * towerHeightPercent;
        double towerSize = Math.min(towerWidth, towerHeight);
        double towerStartY = (startY + (height - towerSize) / 2);
        double gap = (WindowState.MAP_WIDTH * panelWidth - towerSize * maxTowerButtons) / (maxTowerButtons - 1);
        g.setColor(ColorHandler.TOWER_BUTTON_BACKGROUND);
        for (int nr = controllerStateValues.getTowerPanelStartIndex(); nr < maxTowerButtons + controllerStateValues.getTowerPanelStartIndex(); nr++) {
            int index = nr % towerButtons.length;
            int towerStartX = (int) (PERCENT_START_X * panelWidth + startX + gap * index + index * towerSize);
            drawTower(g, nr, towerStartX, (int) towerStartY, (int) towerSize);
        }

        double arrowHeight = .5 * height;
        double arrowY = startY + (height - arrowHeight) / 2;
        double arrowWidth = (width - towerSize * maxTowerButtons - gap * (maxTowerButtons - 1)) / 2 * .9;
        double arrowLeftX = startX + width * .05 - arrowWidth;
        double arrowRightX = startX + width * .95;
        drawArrows(g, (int) arrowLeftX, (int) arrowRightX, (int) arrowY, (int) arrowWidth, (int) arrowHeight);
    }

    private void drawTower(Graphics g, int nr, int x, int y, int towerSize) {
        int index = nr % towerButtons.length;
        //Calculates the xPos for the towerButton
        //Sets the button on the right spot
        towerButtons[index].setSize((towerSize), (towerSize));
        towerButtons[index].setLocation(x, y);
        //Adds a background
        g.fillRect(x, y, towerSize, towerSize);
        //Paints a tower if there is a sprite for it
        Class<? extends Tower>[] towerTypes = controllerStateValues.getAllTowerTypes();
        if (nr < towerTypes.length) {
            BufferedImage tempImage = ImageHandler.getImage(towerImagePathMap.get(towerTypes[nr]), Math.toRadians(90));
            g.drawImage(tempImage, (int) (x + towerSize * 0.05), (int) (y + towerSize * 0.05),
                    (int) (towerSize * 0.9), (int) (towerSize * 0.9), null);
            //Populate the label if there is a tower there
            drawPriceLabel(x, y, towerSize, nr, controllerStateValues.getTowerPrice(towerTypes[nr]));
        } else {
            drawPriceLabel(x, y, towerSize, nr, -1);
        }

    }

    private void drawArrows(Graphics g, int arrowLeftX, int arrowRightX, int arrowY, int arrowWidth, int arrowHeight) {

        if (controllerStateValues.getTowerPanelStartIndex() != 0) {
            g.drawImage(ImageHandler.getImage(Config.ImagePath.ARROW_LEFT), arrowLeftX, arrowY,
                    arrowWidth, arrowHeight, null);
            arrowButtons[0].setLocation(arrowLeftX, arrowY);
            arrowButtons[0].setSize(arrowWidth, arrowHeight);
        }

        if (controllerStateValues.getTowerPanelStartIndex() + towerButtons.length < controllerStateValues.getTotalAmountOfTowers()) {
            g.drawImage(ImageHandler.getImage(Config.ImagePath.ARROW_RIGHT), arrowRightX, arrowY,
                    arrowWidth, arrowHeight, null);
            arrowButtons[1].setLocation(arrowRightX, arrowY);
            arrowButtons[1].setSize(arrowWidth, arrowHeight);
        }
    }

    private void drawPriceLabel(int towerStartX, double towerStartY, double towerSize, int index, int price) {
        int i = index % towerPriceLabels.length;
        if (price == -1) {
            towerPriceLabels[i].setText("");
            towerPriceLabels[i].setBackground(ColorHandler.INVISIBLE);
        } else {
            towerPriceLabels[i].setText("$" + price);
            towerPriceLabels[i].setBackground(ColorHandler.TOWER_BUTTON_LABEL);
        }
        towerPriceLabels[i].setLocation(towerStartX, (int) (towerStartY + towerSize * 0.8));
        towerPriceLabels[i].setFont(new Font("serif", Font.BOLD, (int) (towerSize * 0.2)));
        towerPriceLabels[i].setSize((int) towerSize, (int) (Math.round(towerSize * 0.2)));
    }
}
