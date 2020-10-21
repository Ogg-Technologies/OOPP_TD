package view.gameView.layers.GUIObjects;

import config.Config;
import model.game.tower.Tower;
import model.game.tower.concretetowers.*;
import view.ColorHandler;
import view.ControllerStateValues;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Samuel, Erik
 * <p>
 * Draws the tower info panel to the left containing information about selected tower
 */
public class TowerInfoDrawer {

    /**
     * Position of the panel
     */
    private static final double LEFT = .01;
    private static final double TOP = .1;
    private static final double WIDTH = .12;
    private static final double HEIGHT = WindowState.MAP_HEIGHT - TOP - .01;

    /**
     * String constants used when displaying info
     */
    private static final String NEW_LINE = "<br>";
    private static final String BUFF_PREFIX = "Buff: ";

    private final JLabel infoText;
    private final Map<Class<? extends Tower>, String> towerImagePathMap;
    private final Map<Class<? extends Tower>, String> towerInfoStringMap;

    private ControllerStateValues controllerStateValues;
    private Class<? extends Tower> lastSelectedTower;


    public TowerInfoDrawer(JLabel infoText, Map<Class<? extends Tower>, String> towerInfoStringMap) {
        this.infoText = infoText;
        this.towerImagePathMap = towerInfoStringMap;
        this.towerInfoStringMap = setupInfoStringMap();
    }

    /**
     * Defines the information text to be displayed for every tower when it is selected
     *
     * @return A map containing every tower as key to its information String as value
     */
    private Map<Class<? extends Tower>, String> setupInfoStringMap() {
        Map<Class<? extends Tower>, String> map = new HashMap<>();

        map.put(GrizzlyBear.class, "Grizzly Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.GrizzlyBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.GrizzlyBear.RANGE) + NEW_LINE
                + createDamageString(Config.GrizzlyBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.GrizzlyBear.INFO_TEXT);

        map.put(BearryPotter.class, "Bearry Potter" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.BearryPotter.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.BearryPotter.RANGE) + NEW_LINE
                + createDamageString(Config.BearryPotter.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.BearryPotter.INFO_TEXT);

        map.put(SniperBear.class, "Sniper Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.SniperBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.SniperBear.RANGE) + NEW_LINE
                + createDamageString(Config.SniperBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.SniperBear.INFO_TEXT);

        map.put(SovietBear.class, "Soviet Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.SovietBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.SovietBear.RANGE) + NEW_LINE
                + createDamageString(Config.SovietBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.SovietBear.INFO_TEXT);

        map.put(Barbearian.class, "Barbearian" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.Barbearian.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.Barbearian.RANGE) + NEW_LINE
                + createDamageString(Config.Barbearian.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.Barbearian.INFO_TEXT);

        map.put(BearGrylls.class, "Bear Grylls" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.BearGrylls.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.BearGrylls.RANGE) + NEW_LINE
                + createRangeBuffString(Config.BearGrylls.BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
                + Config.BearGrylls.INFO_TEXT);

        map.put(Beer.class, "Beer" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.Beer.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.Beer.RANGE) + NEW_LINE
                + createDamageBuffString(Config.Beer.DAMAGE_BUFF_PERCENTAGE) + NEW_LINE
                + createFireRateBuffString(Config.Beer.FIRE_RATE_BUFF_PERCENTAGE) + NEW_LINE
                + createRangeBuffString(Config.Beer.RANGE_BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
                + Config.Beer.INFO_TEXT);

        map.put(RubixCubeBear.class, "Rubix Cube Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.RubixCubeBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.RubixCubeBear.RANGE) + NEW_LINE
                + createFireRateBuffString(Config.RubixCubeBear.BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
                + Config.RubixCubeBear.INFO_TEXT);

        map.put(BazookaBear.class, "Bazooka Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.BazookaBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.BazookaBear.RANGE) + NEW_LINE
                + createDamageString(Config.BazookaBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.BazookaBear.INFO_TEXT);

        map.put(Bearon.class, "Bearon" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.Bearon.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.Bearon.RANGE) + NEW_LINE
                + createMoneyString(Config.Bearon.COINS_PER_TOWER) + NEW_LINE + NEW_LINE
                + Config.Bearon.INFO_TEXT);

        return map;
    }


    /**
     * This method (and the following 5 or so) are convenience methods for displaying different information in a consistent manner
     *
     * @param attackDelay The attack delay value for the tower
     * @return A nicely formatted String explaining the attack delay stat for the tower
     */
    private String createFireRateString(int attackDelay) {
        return "Fire Rate: " + String.format("%.2f", attackDelay / 60.0) + "/s";
    }

    private String createRangeString(double range) {
        return "Range: " + String.format("%.1f", range) + " tiles";
    }

    private String createDamageString(int damage) {
        return "Damage: " + damage;
    }

    private String createDamageBuffString(int percentage) {
        return BUFF_PREFIX + percentage + "% Damage";
    }

    private String createRangeBuffString(int percentage) {
        return BUFF_PREFIX + percentage + "% Range";
    }

    private String createFireRateBuffString(int percentage) {
        return BUFF_PREFIX + percentage + "% Fire Rate";
    }

    private String createMoneyString(int coinsPerTower) {
        return "Money/Tower: " + coinsPerTower + "Per tower in range";
    }

    public void draw(Graphics g, int panelWidth, int panelHeight) {
        int x = (int) (LEFT * panelWidth);
        int y = (int) (TOP * panelHeight);
        int width = (int) (WIDTH * panelWidth);
        int height = (int) (HEIGHT * panelHeight);
        int margin = (int) (0.01 * panelWidth);

        g.setColor(ColorHandler.INFO_PANEL_BACKGROUND);
        g.fillRect(x, y, width, height);


        Class<? extends Tower> towerType = getLastSelectedTower();
        if (towerType == null) {
            return;
        }

        drawTowerImage(g, towerImagePathMap.get(towerType), (int) (x + width * 0.05), (int) (y + width * 0.05), (int) (width * 0.9));
        drawTowerInfoText(x + margin, y + width, width - 2 * margin, height - 2 * margin);
    }

    private Class<? extends Tower> getLastSelectedTower() {
        if (controllerStateValues == null) {
            throw new IllegalStateException("controllerStateValues has not been set");
        }
        Class<? extends Tower> towerType = controllerStateValues.getSelectedTowerType();

        if (towerType != null) {
            lastSelectedTower = towerType;
        }
        return lastSelectedTower;
    }

    private void drawTowerImage(Graphics g, String imagePath, int x, int y, int size) {
        g.drawImage(ImageHandler.getImage(imagePath, Math.PI / 2), x, y, size, size, null);
    }

    private void drawTowerInfoText(int x, int y, int width, int height) {
        String text = towerInfoStringMap.getOrDefault(lastSelectedTower, "No info text added yet");
        infoText.setLocation(x, y);
        infoText.setFont(new Font("serif", Font.BOLD, (int) (width * 0.1)));
        infoText.setSize(width, height);
        infoText.setText("<html>" + text + "</html>");
    }

    /**
     * A setter for controllerStateValue
     *
     * @param controllerStateValues an interface where information about towers can be fetched
     */
    public void setControllerStateValues(ControllerStateValues controllerStateValues) {
        this.controllerStateValues = controllerStateValues;
    }
}
