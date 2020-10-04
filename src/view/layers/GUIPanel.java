package view.layers;

import model.ModelData;
import model.ModelInputListener;
import model.game.tower.Tower;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.SniperBear;
import view.ButtonClickHandler;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sebastian, Samuel, Erik
 * Displays gui elemets.
 * Is used by SwingView.
 */

public class GUIPanel extends JPanel {


    private static final Color GUI_BACKGROUND_COLOR = new Color(196, 196, 196);

    private final ModelData modelData;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
        drawMoneyDisplay(g);
        drawNextWaveButton(g);
        drawTowerPanel(g);
    }

    /**
     * Sets up every gui element, except button controller part
     *
     * @param modelData data from model that gui needs.
     */
    public GUIPanel(ModelData modelData) {
        this.modelData = modelData;
        setLayout(null);
        createButtons();
        setupLabels();
        valueSetups();
    }

    private void valueSetups() {
        towerPricesSetup();
    }

    private void setupLabels() {
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(moneyLabel);
        healthBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(healthBarLabel);
        towerPriceLabelSetup();
    }

    private void createButtons() {
        nextWaveButton.setBorder(BorderFactory.createEmptyBorder());
        nextWaveButton.setContentAreaFilled(false);
        add(nextWaveButton);
        createTowerButtons();
    }

    /**
     * Sets up all buttons to talk with a controller
     *
     * @param buttonClickHandler the controller that handles the onClicks.
     * @param methodGiver        methods from model that will happen when button is clicked.
     */
    public void setupButtons(ButtonClickHandler buttonClickHandler, ModelInputListener methodGiver) {
        //nextWaveButton
        nextWaveButton.addActionListener(e -> buttonClickHandler.onNextWaveClicked());
        for (int i = 0; i < towerClasses.length; i++) {
            int finalI = i;
            towerButtons[i].addActionListener((e -> buttonClickHandler.setSelectedTower(towerClasses[finalI])));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BaseHealth data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color HEALTH_COLOR = Color.GREEN;

    private static final double HEALTH_BAR_LEFT = .145;
    private static final double HEALTH_BAR_UP = .02;
    private static final double HEALTH_BAR_WIDTH = .01;
    private static final double HEALTH_BAR_HEIGHT = .83;
    private static final double HEALTH_LABEL_LEFT = HEALTH_BAR_LEFT - .02;
    private static final double HEALTH_LABEL_UP = HEALTH_BAR_UP + HEALTH_BAR_HEIGHT;
    private static final double HEALTH_LABEL_WIDTH = HEALTH_BAR_WIDTH + .04;
    private static final double HEALTH_LABEL_HEIGHT = .04;
    private final JLabel healthBarLabel = new JLabel();

    private void drawHealthBar(Graphics g) {
        int x = (int) (HEALTH_BAR_LEFT * getWidth());
        int fullY = (int) (HEALTH_BAR_UP * getHeight());
        int width = (int) (HEALTH_BAR_WIDTH * getWidth());
        int fullHeight = (int) (HEALTH_BAR_HEIGHT * getHeight());
        int fractionY = (int) (fullY + fullHeight * (1 - modelData.getBaseHealth().getFraction()));
        int fractionHeight = (int) (fullHeight * modelData.getBaseHealth().getFraction());
        g.setColor(GUI_BACKGROUND_COLOR);
        g.fillRect(x, fullY, width, fullHeight);

        g.setColor(HEALTH_COLOR);
        g.fillRect(x, fractionY, width, fractionHeight);

        int healthBarLabelWidth = (int) (HEALTH_LABEL_WIDTH * getWidth());
        healthBarLabel.setLocation((int) (HEALTH_LABEL_LEFT * getWidth()), (int) (HEALTH_LABEL_UP * getHeight()));
        healthBarLabel.setSize(healthBarLabelWidth, (int) (HEALTH_LABEL_HEIGHT * getHeight()));
        healthBarLabel.setText("" + (int) (Math.round(modelData.getBaseHealth().getFraction() * 100)) + "%");
        int fontSize = (int) (HEALTH_LABEL_HEIGHT * getHeight() / 2);
        healthBarLabel.setFont(new Font("serif", Font.PLAIN, fontSize));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //MoneyLabel data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double MONEY_LEFT = .01;
    private static final double MONEY_UP = .02;
    private static final double MONEY_WIDTH = .12;
    private static final double MONEY_HEIGHT = .06;
    private final JLabel moneyLabel = new JLabel();

    private void drawMoneyDisplay(Graphics g) {
        int x = (int) (MONEY_LEFT * getWidth());
        int y = (int) (MONEY_UP * getHeight());
        int width = (int) (MONEY_WIDTH * getWidth());
        int height = (int) (MONEY_HEIGHT * getHeight());
        int fontSize = (int) (width / 4.2); //.2 because some sort of margin
        g.setColor(GUI_BACKGROUND_COLOR);
        g.fillRect(x, y, width, height);
        moneyLabel.setLocation(x, y);
        moneyLabel.setSize(width, height);
        moneyLabel.setText("$ " + modelData.getMoney());
        moneyLabel.setFont(new Font("serif", Font.BOLD, fontSize));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NextWaveButton data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double WAVE_BUTTON_LEFT = .87;
    private static final double WAVE_BUTTON_UP = .77;
    private static final double WAVE_BUTTON_WIDTH = .12;
    private static final double WAVE_BUTTON_HEIGHT = .11;
    private static final Color BACKGROUND_WAVE_BUTTON = Color.decode("#979797");
    private final JButton nextWaveButton = new JButton();

    private void drawNextWaveButton(Graphics g) {
        int startX = (int) (WAVE_BUTTON_LEFT * getWidth());
        int startY = (int) (WAVE_BUTTON_UP * getHeight());
        int width = (int) (WAVE_BUTTON_WIDTH * getWidth());
        int height = (int) (WAVE_BUTTON_HEIGHT * getHeight());
        nextWaveButton.setSize(width, height);
        nextWaveButton.setLocation(startX, startY);
        g.setColor(BACKGROUND_WAVE_BUTTON);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TowerPanel data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color BACKGROUND_TOWER_PANEL = Color.decode("#CC9966");
    private static final Color TOWER_BACKGROUND = Color.decode("#ecd9c6");
    private static final int MAX_TOWERS = 8;
    private final JLabel[] towerPriceLabels = new JLabel[MAX_TOWERS];
    private final JButton[] towerButtons = new JButton[MAX_TOWERS];
    private final int[] towerPrices = new int[MAX_TOWERS];
    private final Class<? extends Tower>[] towerClasses = new Class[]{
            GrizzlyBear.class, BearryPotter.class, SniperBear.class
    };
    private final String[] towerImagePaths = new String[]{
            "grizzlyBear.png", "mageBear.png", "sniperBear.png"
    };

    private void drawTowerPanel(Graphics g) {
        double percentStartY = (WindowState.MAP_HEIGHT + WindowState.MAP_UP - 0.001);
        double percentStartX = WAVE_BUTTON_LEFT - WindowState.MAP_WIDTH - WindowState.MAP_LEFT;
        int startX = (int) ((WindowState.MAP_LEFT - percentStartX) * getWidth());
        int startY = (int) (percentStartY * getHeight());
        int width = (int) ((WindowState.MAP_WIDTH + 2 * percentStartX) * getWidth());
        int height = (int) ((1 - percentStartY) * getHeight());
        g.setColor(BACKGROUND_TOWER_PANEL);
        g.fillRect(startX, startY, width, height);

        double towerHeightPercent = 0.90;
        double towerWidth = (WindowState.MAP_WIDTH * getWidth() / MAX_TOWERS) - 2;
        double towerHeight = height * towerHeightPercent;
        double towerSize = Math.min(towerWidth, towerHeight);
        double towerStartY = (startY + (height - towerSize) / 2);
        double gap = (WindowState.MAP_WIDTH * getWidth() - towerSize * MAX_TOWERS) / (MAX_TOWERS - 1);
        g.setColor(TOWER_BACKGROUND);
        for (int nr = 0; nr < MAX_TOWERS; nr++) {
            //Calculates the xPos for the towerButton
            int towerStartX = (int) (percentStartX * getWidth() + startX + gap * nr + nr * towerSize);
            //Sets the button on the right spot
            towerButtons[nr].setSize((int) (towerSize), (int) (towerSize));
            towerButtons[nr].setLocation(towerStartX, (int) (towerStartY));
            //Adds a background
            g.fillRect(towerStartX, (int) towerStartY, (int) towerSize, (int) towerSize);
            //Paints a tower if there is a sprite for it
            if (nr < towerImagePaths.length) {
                BufferedImage tempImage = ImageHandler.getImage("resource/" + towerImagePaths[nr], Math.toRadians(90));
                g.drawImage(tempImage, (int) (towerStartX + towerSize * 0.05), (int) (towerStartY + towerSize * 0.05),
                        (int) (towerSize * 0.9), (int) (towerSize * 0.9), null);
            }
            //Populate the label if there is a tower there
            drawPriceLabel(towerStartX, towerStartY, towerSize, nr);
        }
    }

    private static final Color TOWER_MONEY_LABEL_BACKGROUND = new Color(196, 196, 196, 180);
    private static final Color INVISIBLE_COLOR = new Color(0, 0, 0, 0);

    //TODO make this compatible for more than maxTowers
    private void drawPriceLabel(int towerStartX, double towerStartY, double towerSize, int index) {
        if (index < towerPrices.length) {
            towerPriceLabels[index].setText("$" + towerPrices[index]);
            towerPriceLabels[index].setBackground(TOWER_MONEY_LABEL_BACKGROUND);
        } else {
            towerPriceLabels[index].setText("");
            towerPriceLabels[index].setBackground(INVISIBLE_COLOR);

        }
        towerPriceLabels[index].setLocation(towerStartX, (int) (towerStartY + towerSize * 0.8));
        towerPriceLabels[index].setFont(new Font("serif", Font.BOLD, (int) (towerSize * 0.2)));
        towerPriceLabels[index].setSize((int) towerSize, (int) (Math.round(towerSize * 0.2)));
    }

    private void createTowerButtons() {
        for (int i = 0; i < towerButtons.length; i++) {
            JButton tempButton = new JButton();
            tempButton.setBorder(BorderFactory.createEmptyBorder());
            tempButton.setContentAreaFilled(false);
            add(tempButton);
            towerButtons[i] = tempButton;

        }
    }

    private void towerPriceLabelSetup() {
        for (int i = 0; i < towerPriceLabels.length; i++) {
            JLabel tempLabel = new JLabel();
            tempLabel.setVerticalAlignment(SwingConstants.CENTER);
            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tempLabel.setOpaque(true);
            towerPriceLabels[i] = tempLabel;
            add(tempLabel);
        }
    }

    private void towerPricesSetup() {
        for (int i = 0; i < towerClasses.length; i++) {
            towerPrices[i] = modelData.getTowerPrice(towerClasses[i]);
        }
    }
}
