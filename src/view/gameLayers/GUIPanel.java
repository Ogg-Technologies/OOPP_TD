package view.gameLayers;

import application.Constant;
import model.ModelData;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;
import utils.Vector;
import view.ButtonClickHandler;
import view.ControllerStateValue;
import view.WindowState;
import view.gameLayers.GUIObjects.*;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
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

    private final int MAX_TOWERS = 8;

    //Buttons
    private final JButton[] towerButtons;
    private final JButton nextWaveButton;
    private final JButton backToStartButton;
    private final JButton[] arrowButtons;


    //GUI objects
    private final GhostTowerDrawer ghostTowerDrawer;
    private final BarsDrawer barsDrawer;
    private final MoneyLabelDrawer moneyLabelDrawer;
    private final NextWaveButtonDrawer waveButtonDrawer;
    private final TowerPanelDrawer towerPanelDrawer;
    private final WaveLabelDrawer waveLabelDrawer;
    private final BackToStartButtonDrawer backToStartButtonDrawer;


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

        JLabel playerHealthLabel = new JLabel();
        playerHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerHealthLabel);
        JLabel enemyHealthLabel = new JLabel();
        enemyHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enemyHealthLabel);
        barsDrawer = new BarsDrawer(playerHealthLabel, enemyHealthLabel);

        JLabel moneyLabel = new JLabel();
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(moneyLabel);
        moneyLabelDrawer = new MoneyLabelDrawer(moneyLabel);

        nextWaveButton = new JButton();
        nextWaveButton.setBorder(BorderFactory.createEmptyBorder());
        nextWaveButton.setContentAreaFilled(false);
        add(nextWaveButton);
        waveButtonDrawer = new NextWaveButtonDrawer(nextWaveButton);

        towerButtons = towerButtonsSetup();
        arrowButtons = arrowButtonsSetup();
        towerPanelDrawer = new TowerPanelDrawer(towerButtons, towerPriceLabelSetup(), arrowButtons);

        JLabel waveLabel = new JLabel();
        waveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(waveLabel);
        waveLabelDrawer = new WaveLabelDrawer(waveLabel);

        backToStartButton = new JButton();
        backToStartButton.setText("X");
        add(backToStartButton);
        backToStartButtonDrawer = new BackToStartButtonDrawer(backToStartButton);

        setLayout(null);
    }

    private JButton[] arrowButtonsSetup() {
        JButton[] arrowButtons = new JButton[2];
        for (int i = 0; i < arrowButtons.length; i++) {
            JButton tempButton = new JButton();
            tempButton.setBorder(BorderFactory.createEmptyBorder());
            tempButton.setContentAreaFilled(false);
            add(tempButton);
            arrowButtons[i] = tempButton;
        }
        return arrowButtons;
    }


    /**
     * Update the saved value of mouse pos
     *
     * @param mouseTilePos new mouse tilePos
     */
    public void updateMouseTilePos(Vector mouseTilePos) {
        this.mouseTilePos = mouseTilePos;
    }

    /**
     * Sets up all buttons to talk with a controller
     *
     * @param buttonClickHandler the controller that handles the onClicks.
     */
    public void setupButtons(ButtonClickHandler buttonClickHandler, WindowState windowState) {
        //nextWaveButton
        nextWaveButton.addActionListener(e -> buttonClickHandler.onNextWaveClicked());
        for (int i = 0; i < MAX_TOWERS; i++) {
            int finalI = i;
            towerButtons[i].addActionListener((e -> buttonClickHandler.setSelectedTowerIndexButton(finalI)));
        }
        backToStartButton.addActionListener(e -> windowState.setViewStateToStart());
        arrowButtons[0].addActionListener(e -> controllerStateValue.changeStartIndex(-MAX_TOWERS));
        arrowButtons[1].addActionListener(e -> controllerStateValue.changeStartIndex(MAX_TOWERS));
    }

    private JButton[] towerButtonsSetup() {
        JButton[] returnButtons = new JButton[MAX_TOWERS];
        for (int i = 0; i < returnButtons.length; i++) {
            JButton tempButton = new JButton();
            tempButton.setBorder(BorderFactory.createEmptyBorder());
            tempButton.setContentAreaFilled(false);
            add(tempButton);
            returnButtons[i] = tempButton;
        }
        return returnButtons;
    }

    private JLabel[] towerPriceLabelSetup() {
        JLabel[] returnLabels = new JLabel[MAX_TOWERS];
        for (int i = 0; i < MAX_TOWERS; i++) {
            JLabel tempLabel = new JLabel();
            tempLabel.setVerticalAlignment(SwingConstants.CENTER);
            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tempLabel.setOpaque(true);
            returnLabels[i] = tempLabel;
            add(tempLabel);
        }
        return returnLabels;
    }

    /**
     * Adds a state for some controller values
     *
     * @param controllerStateValue the state
     */
    public void addState(ControllerStateValue controllerStateValue) {
        this.controllerStateValue = controllerStateValue;
        towerPanelDrawer.setControllerStateValue(controllerStateValue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controllerStateValue.getSelectedTowerType() != null && mouseTilePos != null) {
            ghostTowerDrawer.draw(g, controllerStateValue.getSelectedTowerType(), controllerStateValue.getSelectedTowerRange(), mouseTilePos);
        }
        barsDrawer.draw(g, getWidth(), getHeight(), modelData.getBaseHealth().getFraction(),
                modelData.getEnemyAttackHealth().getFraction());
        moneyLabelDrawer.draw(g, getWidth(), getHeight(), modelData.getMoney());
        waveButtonDrawer.draw(g, getWidth(), getHeight());
        towerPanelDrawer.draw(g, getWidth(), getHeight());
        waveLabelDrawer.draw(getWidth(), getHeight(), modelData.getWaveNumber());
        backToStartButtonDrawer.draw(getWidth(), getHeight());
    }
}
