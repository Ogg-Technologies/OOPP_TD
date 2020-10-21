package view.gameView.layers;

import config.Config;
import model.ModelData;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;
import utils.Vector;
import view.ButtonClickHandler;
import view.ControllerStateValues;
import view.OnClose;
import view.WindowState;
import view.gameView.layers.GUIObjects.*;
import view.mainMenuView.MainMenuView;

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
    private static final Map<Class<? extends Tower>, String> towerPathMap = setupPathMap();

    private static Map<Class<? extends Tower>, String> setupPathMap() {
        HashMap<Class<? extends Tower>, String> pathMap = new HashMap<>();
        pathMap.put(GrizzlyBear.class, Config.ImagePath.GRIZZLY_BEAR);
        pathMap.put(BearryPotter.class, Config.ImagePath.BEARRY_POTTER);
        pathMap.put(SniperBear.class, Config.ImagePath.SNIPER_BEAR);
        pathMap.put(SovietBear.class, Config.ImagePath.SOVIET_BEAR);
        pathMap.put(Barbearian.class, Config.ImagePath.BARBEARIAN);
        pathMap.put(BearGrylls.class, Config.ImagePath.BEAR_GRYLLS);
        pathMap.put(Beer.class, Config.ImagePath.BEER_BEAR);
        pathMap.put(RubixCubeBear.class, Config.ImagePath.RUBIX_CUBE_BEAR);
        pathMap.put(BazookaBear.class, Config.ImagePath.BAZOOKA_BEAR);
        return pathMap;
    }

    private final ModelData modelData;

    private Vector mouseTilePos;

    private ControllerStateValues controllerStateValues;

    public static final int MAX_TOWERS = 8;

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
    private final TowerInfoDrawer towerInfoDrawer;


    /**
     * Sets up every gui element, except button controller part
     *
     * @param modelData   data from model that gui needs.
     * @param windowState the state of window
     */
    public GUIPanel(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        ghostTowerDrawer = new GhostTowerDrawer(windowState, towerPathMap);

        RotatingLabel playerHealthLabel = new RotatingLabel(Math.PI / 2);
        playerHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerHealthLabel);
        RotatingLabel enemyHealthLabel = new RotatingLabel(-Math.PI / 2 );
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
        towerPanelDrawer = new TowerPanelDrawer(towerButtons, towerPriceLabelSetup(), arrowButtons, towerPathMap);

        JLabel waveLabel = new JLabel();
        waveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(waveLabel);
        waveLabelDrawer = new WaveLabelDrawer(waveLabel);

        backToStartButton = new JButton();
        backToStartButton.setText("X");
        add(backToStartButton);
        backToStartButtonDrawer = new BackToStartButtonDrawer(backToStartButton);

        JLabel infoText = new JLabel();
        infoText.setHorizontalAlignment(SwingConstants.LEFT);
        infoText.setVerticalAlignment(SwingConstants.TOP);
        add(infoText);
        towerInfoDrawer = new TowerInfoDrawer(infoText, towerPathMap);

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
    public void setupButtons(ButtonClickHandler buttonClickHandler, OnClose onClose) {
        //nextWaveButton
        nextWaveButton.addActionListener(e -> buttonClickHandler.onNextWaveClicked());
        for (int i = 0; i < MAX_TOWERS; i++) {
            int finalI = i;
            towerButtons[i].addActionListener((e -> buttonClickHandler.setSelectedTowerIndexButton(finalI)));
        }
        backToStartButton.addActionListener(e -> onClose.close());
        arrowButtons[0].addActionListener(e -> controllerStateValues.changeStartIndex(-MAX_TOWERS));
        arrowButtons[1].addActionListener(e -> controllerStateValues.changeStartIndex(MAX_TOWERS));
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
     * @param controllerStateValues the state
     */
    public void addState(ControllerStateValues controllerStateValues) {
        this.controllerStateValues = controllerStateValues;
        towerPanelDrawer.setControllerStateValues(controllerStateValues);
        towerInfoDrawer.setControllerStateValues(controllerStateValues);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controllerStateValues.getSelectedTowerType() != null && mouseTilePos != null) {
            ghostTowerDrawer.draw(g, controllerStateValues.getSelectedTowerType(), controllerStateValues.getSelectedTowerRange(), mouseTilePos);
        }
        barsDrawer.draw(g, getWidth(), getHeight(), modelData.getBaseHealth(), modelData.getEnemyAttackHealth());
        moneyLabelDrawer.draw(g, getWidth(), getHeight(), modelData.getMoney());
        waveButtonDrawer.draw(g, getWidth(), getHeight());
        towerPanelDrawer.draw(g, getWidth(), getHeight());
        waveLabelDrawer.draw(getWidth(), getHeight(), modelData.getWaveNumber());
        backToStartButtonDrawer.draw(getWidth(), getHeight());
        towerInfoDrawer.draw(g, getWidth(), getHeight());
    }
}
