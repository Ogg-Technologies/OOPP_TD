package view.gameLayers;

import application.Constant;
import model.ModelData;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;
import utils.Vector;
import view.ButtonClickHandler;
import view.ColorHandler;
import view.ControllerStateValue;
import view.WindowState;
import view.gameLayers.GUIObjects.GhostTowerDrawer;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oskar, Sebastian, Samuel, Erik
 * Displays gui elemets.
 * Is used by SwingView.
 */

public class GUIPanel extends JPanel {

    /**
     * A map used by a lot of GUI elements (in this class and in GUIObjects) to know which path to use for displaying
     * an image.
     */
    public static Map<Class<? extends Tower>, String> towerPathMap = setupPathMap();

    private static Map<Class<? extends Tower>, String> setupPathMap() {
        HashMap<Class<? extends Tower>, String> pathMap = new HashMap<>();
        pathMap.put(GrizzlyBear.class, Constant.getInstance().IMAGE_PATH.GRIZZLY_BEAR);
        pathMap.put(BearryPotter.class, Constant.getInstance().IMAGE_PATH.BEARRY_POTTER);
        pathMap.put(SniperBear.class, Constant.getInstance().IMAGE_PATH.SNIPER_BEAR);
        pathMap.put(SovietBear.class, Constant.getInstance().IMAGE_PATH.SOVIET_BEAR);
        pathMap.put(Barbearian.class, Constant.getInstance().IMAGE_PATH.BARBEARIAN);
        return pathMap;
    }

    private final ModelData modelData;

    private Vector mouseTilePos;

    private ControllerStateValue controllerStateValue;

    private final GhostTowerDrawer ghostTowerDrawer;

    private JButton backToStartButton;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //EnemyAttack health
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double ENEMY_ATTACK_HEALTH_BAR_LEFT = 0.845;

    /**
     * Sets up every gui element, except button controller part
     *
     * @param modelData   data from model that gui needs.
     * @param windowState the state of window
     */
    public GUIPanel(ModelData modelData, WindowState windowState, ControllerStateValue controllerStateValue) {
        this.modelData = modelData;
        this.controllerStateValue = controllerStateValue;
        ghostTowerDrawer = new GhostTowerDrawer(windowState);
        setLayout(null);
        createButtons();
        setupLabels();
    }

    /**
     * Update the saved value of mouse pos
     *
     * @param mouseTilePos new mouse tilePos
     */
    public void updateMouseTilePos(Vector mouseTilePos) {
        this.mouseTilePos = mouseTilePos;
    }

    private static final double ENEMY_ATTACK_HEALTH_BAR_UP = .02;

    private void createButtons() {
        nextWaveButton.setBorder(BorderFactory.createEmptyBorder());
        nextWaveButton.setContentAreaFilled(false);
        add(nextWaveButton);
        createTowerButtons();
        backToStartButton = new JButton();
        backToStartButton.setText("X");
        add(backToStartButton);
    }

    /**
     * Sets up all buttons to talk with a controller
     *
     * @param buttonClickHandler the controller that handles the onClicks.
     */
    public void setupButtons(ButtonClickHandler buttonClickHandler, WindowState windowState) {
        //nextWaveButton
        nextWaveButton.addActionListener(e -> buttonClickHandler.onNextWaveClicked());
        Class<? extends Tower>[] towerTypes = controllerStateValue.getAllTowerTypes();
        for (int i = 0; i < towerTypes.length; i++) {
            int finalI = i;
            towerButtons[i].addActionListener((e -> buttonClickHandler.setSelectedTower(towerTypes[finalI])));
        }
        backToStartButton.addActionListener(e -> windowState.setViewStateToStart());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BackToStartButton data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final double BACK_TO_START_LEFT = .93;
    private final double BACK_TO_START_UP = .02;
    private final double BACK_TO_START_WIDTH = .05;
    private final double BACK_TO_START_HEIGHT = .05;
    private final double BACK_TO_START_FONT = .22;

    private void updateBackToStartButton() {
        backToStartButton.setSize((int)(BACK_TO_START_WIDTH * getWidth()), (int)(BACK_TO_START_HEIGHT * getHeight()));
        backToStartButton.setLocation((int)(BACK_TO_START_LEFT * getWidth()), (int)(BACK_TO_START_UP * getHeight()));
        backToStartButton.setFont(new Font("serif", Font.BOLD, (int)(BACK_TO_START_FONT * getWidth() * BACK_TO_START_WIDTH)));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BaseHealth data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        g.setColor(ColorHandler.STANDARD_GUI_BACKGROUND);
        g.fillRect(x, fullY, width, fullHeight);

        g.setColor(ColorHandler.PLAYER_HEALTH);
        g.fillRect(x, fractionY, width, fractionHeight);

        int healthBarLabelWidth = (int) (HEALTH_LABEL_WIDTH * getWidth());
        healthBarLabel.setLocation((int) (HEALTH_LABEL_LEFT * getWidth()), (int) (HEALTH_LABEL_UP * getHeight()));
        healthBarLabel.setSize(healthBarLabelWidth, (int) (HEALTH_LABEL_HEIGHT * getHeight()));
        healthBarLabel.setText("" + (int) (Math.round(modelData.getBaseHealth().getFraction() * 100)) + "%");
        int fontSize = (int) (HEALTH_LABEL_HEIGHT * getHeight() / 2);
        healthBarLabel.setFont(new Font("serif", Font.PLAIN, fontSize));
    }

    private static final double ENEMY_ATTACK_HEALTH_BAR_WIDTH = .01;
    private static final double ENEMY_ATTACK_HEALTH_BAR_HEIGHT = .83;
    private static final double ENEMY_ATTACK_HEALTH_LABEL_LEFT = ENEMY_ATTACK_HEALTH_BAR_LEFT - .02;
    private static final double ENEMY_ATTACK_HEALTH_LABEL_UP = ENEMY_ATTACK_HEALTH_BAR_UP + ENEMY_ATTACK_HEALTH_BAR_HEIGHT;
    private static final double ENEMY_ATTACK_HEALTH_LABEL_WIDTH = ENEMY_ATTACK_HEALTH_BAR_WIDTH + .04;
    private static final double ENEMY_ATTACK_HEALTH_LABEL_HEIGHT = .04;
    private final JLabel enemyAttackHealthBarLabel = new JLabel();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controllerStateValue.getSelectedTowerType() != null && mouseTilePos != null) {
            ghostTowerDrawer.draw(g, controllerStateValue.getSelectedTowerType(), controllerStateValue.getSelectedTowerRange(), mouseTilePos);
        }
        drawHealthBar(g);
        drawEnemyAttackHealthBar(g);
        drawMoneyDisplay(g);
        drawNextWaveButton(g);
        drawTowerPanel(g);
        updateBackToStartButton();
    }

    private void setupLabels() {
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(moneyLabel);
        healthBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(healthBarLabel);
        enemyAttackHealthBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enemyAttackHealthBarLabel);
        towerPriceLabelSetup();
    }

    private void drawEnemyAttackHealthBar(Graphics g) {
        int x = (int) (ENEMY_ATTACK_HEALTH_BAR_LEFT * getWidth());
        int fullY = (int) (ENEMY_ATTACK_HEALTH_BAR_UP * getHeight());
        int width = (int) (ENEMY_ATTACK_HEALTH_BAR_WIDTH * getWidth());
        int fullHeight = (int) (ENEMY_ATTACK_HEALTH_BAR_HEIGHT * getHeight());
        int fractionY = (int) (fullY + fullHeight * (1 - modelData.getEnemyAttackHealth().getFraction()));
        int fractionHeight = (int) (fullHeight * modelData.getEnemyAttackHealth().getFraction());
        g.setColor(ColorHandler.STANDARD_GUI_BACKGROUND);
        g.fillRect(x, fullY, width, fullHeight);

        g.setColor(ColorHandler.ENEMY_HEALTH);
        g.fillRect(x, fractionY, width, fractionHeight);

        int healthBarLabelWidth = (int) (ENEMY_ATTACK_HEALTH_LABEL_WIDTH * getWidth());
        enemyAttackHealthBarLabel.setLocation((int) (ENEMY_ATTACK_HEALTH_LABEL_LEFT * getWidth()), (int) (ENEMY_ATTACK_HEALTH_LABEL_UP * getHeight()));
        enemyAttackHealthBarLabel.setSize(healthBarLabelWidth, (int) (ENEMY_ATTACK_HEALTH_LABEL_HEIGHT * getHeight()));
        enemyAttackHealthBarLabel.setText("" + (int) (Math.round(modelData.getEnemyAttackHealth().getFraction() * 100)) + "%");
        int fontSize = (int) (ENEMY_ATTACK_HEALTH_LABEL_HEIGHT * getHeight() / 2);
        enemyAttackHealthBarLabel.setFont(new Font("serif", Font.PLAIN, fontSize));
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
        g.setColor(ColorHandler.STANDARD_GUI_BACKGROUND);
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
    private final JButton nextWaveButton = new JButton();

    private void drawNextWaveButton(Graphics g) {
        int startX = (int) (WAVE_BUTTON_LEFT * getWidth());
        int startY = (int) (WAVE_BUTTON_UP * getHeight());
        int width = (int) (WAVE_BUTTON_WIDTH * getWidth());
        int height = (int) (WAVE_BUTTON_HEIGHT * getHeight());
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TowerPanel data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int MAX_TOWERS = 8;
    private final JLabel[] towerPriceLabels = new JLabel[MAX_TOWERS];
    private final JButton[] towerButtons = new JButton[MAX_TOWERS];

    private void drawTowerPanel(Graphics g) {
        double percentStartY = (WindowState.MAP_HEIGHT + WindowState.MAP_UP - 0.001);
        double percentStartX = WAVE_BUTTON_LEFT - WindowState.MAP_WIDTH - WindowState.MAP_LEFT;
        int startX = (int) ((WindowState.MAP_LEFT - percentStartX) * getWidth());
        int startY = (int) (percentStartY * getHeight());
        int width = (int) ((WindowState.MAP_WIDTH + 2 * percentStartX) * getWidth());
        int height = (int) ((1 - percentStartY) * getHeight());
        g.setColor(ColorHandler.TOWER_PANEL);
        g.fillRect(startX, startY, width, height);

        double towerHeightPercent = 0.90;
        double towerWidth = (WindowState.MAP_WIDTH * getWidth() / MAX_TOWERS) - 2;
        double towerHeight = height * towerHeightPercent;
        double towerSize = Math.min(towerWidth, towerHeight);
        double towerStartY = (startY + (height - towerSize) / 2);
        double gap = (WindowState.MAP_WIDTH * getWidth() - towerSize * MAX_TOWERS) / (MAX_TOWERS - 1);
        g.setColor(ColorHandler.TOWER_BUTTON_BACKGROUND);
        Class<? extends Tower>[] towerTypes = controllerStateValue.getAllTowerTypes();
        for (int nr = 0; nr < MAX_TOWERS; nr++) {
            //Calculates the xPos for the towerButton
            int towerStartX = (int) (percentStartX * getWidth() + startX + gap * nr + nr * towerSize);
            //Sets the button on the right spot
            towerButtons[nr].setSize((int) (towerSize), (int) (towerSize));
            towerButtons[nr].setLocation(towerStartX, (int) (towerStartY));
            //Adds a background
            g.fillRect(towerStartX, (int) towerStartY, (int) towerSize, (int) towerSize);
            //Paints a tower if there is a sprite for it
            if (nr < towerPathMap.size() && nr < towerTypes.length) {
                BufferedImage tempImage = ImageHandler.getImage(towerPathMap.get(towerTypes[nr]), Math.toRadians(90));
                g.drawImage(tempImage, (int) (towerStartX + towerSize * 0.05), (int) (towerStartY + towerSize * 0.05),
                        (int) (towerSize * 0.9), (int) (towerSize * 0.9), null);
                //Populate the label if there is a tower there
                drawPriceLabel(towerStartX, towerStartY, towerSize, nr, controllerStateValue.getTowerPrice(towerTypes[nr]));
            } else {
                drawPriceLabel(towerStartX, towerStartY, towerSize, nr, 0);
            }

        }
    }

    //TODO make this compatible for more than maxTowers
    private void drawPriceLabel(int towerStartX, double towerStartY, double towerSize, int index, int price) {
        if (index < towerPathMap.size()) {
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
}
