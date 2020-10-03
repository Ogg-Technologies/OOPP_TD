package view.layers;

import model.ModelData;
import model.ModelInputListener;
import model.game.tower.Tower;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.SniperBear;
import utils.Vector;
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
    private final JLabel moneyLabel = new JLabel();
    private final JLabel healthBarLabel = new JLabel();
    private final JLabel[] towerPriceLabels;
    private final JButton[] towerButtons;
    private final int[] towerPrices;

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
        add(moneyLabel);
        add(healthBarLabel);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        towerPrices = towerPricesSetup();
        towerPriceLabels = towerPriceLabelSetup();
        towerButtons = createTowerButtons();

    }

    private JButton[] createTowerButtons() {
        JButton[] tempArray = new JButton[maxTowers];
        for (int i = 0; i < tempArray.length; i++) {
            JButton tempButton = new JButton();
            tempButton.setBorder(BorderFactory.createEmptyBorder());
            tempButton.setContentAreaFilled(false);
            add(tempButton);
            tempArray[i] = tempButton;

        }
        return tempArray;
    }

    private int[] towerPricesSetup() {
        int[] tempArray = new int[towerImagePaths.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = modelData.getTowerPrice(towerClasses[i]);
        }
        return tempArray;
    }

    private JLabel[] towerPriceLabelSetup() {
        JLabel[] tempArray = new JLabel[maxTowers];
        for (int i = 0; i < tempArray.length; i++) {
            JLabel tempLabel = new JLabel();
            tempLabel.setVerticalAlignment(SwingConstants.CENTER);
            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tempLabel.setOpaque(true);
            tempArray[i] = tempLabel;
            add(tempLabel);
        }
        return tempArray;
    }

    /**
     * Sets up all buttons to talk with a controller
     *
     * @param buttonClickHandler the controller that handles the onClicks.
     * @param methodGiver        methods from model that will happen when button is clicked.
     */
    public void setupButtons(ButtonClickHandler buttonClickHandler, ModelInputListener methodGiver) {
        //nextWaveButton
        buttonClickHandler.addButtonWithoutArgument(new Vector(WAVE_BUTTON_LEFT, WAVE_BUTTON_UP),
                new Vector(WAVE_BUTTON_WIDTH, WAVE_BUTTON_HEIGHT), methodGiver::onStartNewWave);
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

    private void drawNextWaveButton(Graphics g) {
        int startX = (int) (WAVE_BUTTON_LEFT * getWidth());
        int startY = (int) (WAVE_BUTTON_UP * getHeight());
        int width = (int) (WAVE_BUTTON_WIDTH * getWidth());
        int height = (int) (WAVE_BUTTON_HEIGHT * getHeight());
        g.setColor(BACKGROUND_WAVE_BUTTON);
        g.fillRect(startX, startY, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TowerPanel data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color BACKGROUND_TOWER_PANEL = Color.decode("#CC9966");
    private static final Color TOWER_BACKGROUND = Color.decode("#ecd9c6");
    private final int maxTowers = 8;
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
        double towerWidth = (WindowState.MAP_WIDTH * getWidth() / maxTowers) - 2;
        double towerHeight = height * towerHeightPercent;
        double towerSize = Math.min(towerWidth, towerHeight);
        double towerStartY = (startY + (height - towerSize) / 2);
        double gap = (WindowState.MAP_WIDTH * getWidth() - towerSize * maxTowers) / (maxTowers - 1);
        g.setColor(TOWER_BACKGROUND);
        for (int nr = 0; nr < maxTowers; nr++) {
            int towerStartX = (int) (percentStartX * getWidth() + startX + gap * nr + nr * towerSize);
            towerButtons[nr].setSize((int) (towerSize), (int) (towerSize));
            towerButtons[nr].setLocation(towerStartX, (int) (towerStartY));
            g.fillRect(towerStartX, (int) towerStartY, (int) towerSize, (int) towerSize);
            if (nr < towerImagePaths.length) {
                BufferedImage tempImage = ImageHandler.getImage("resource/" + towerImagePaths[nr], Math.toRadians(90));
                g.drawImage(tempImage, (int) (towerStartX + towerSize * 0.05), (int) (towerStartY + towerSize * 0.05),
                        (int) (towerSize * 0.9), (int) (towerSize * 0.9), null);
            }
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
}
