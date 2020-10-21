package view.gameView.layers.GUIObjects;

import config.Config;
import model.game.tower.Tower;
import model.game.tower.concretetowers.GrizzlyBear;
import view.ColorHandler;
import view.ControllerStateValues;
import view.WindowState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Samuel, Erik
 * <p>
 * Draws the tower info panel to the right containing information about selected tower
 */
public class TowerInfoDrawer {

    private static final double LEFT = .01;
    private static final double TOP = .1;
    private static final double WIDTH = .12;
    private static final double HEIGHT = WindowState.MAP_HEIGHT - TOP - .01;
    private static final String NEW_LINE = "<br>";

    private final Map<Class<? extends Tower>, String> towerInfoStringMap;
    private final JLabel infoText;

    private ControllerStateValues controllerStateValues;


    public TowerInfoDrawer(JLabel infoText) {
        this.towerInfoStringMap = setupInfoStringMap();
        this.infoText = infoText;
    }

    private Map<Class<? extends Tower>, String> setupInfoStringMap() {
        Map<Class<? extends Tower>, String> map = new HashMap<>();
        map.put(GrizzlyBear.class, "Grizzly Bear" + NEW_LINE + NEW_LINE
                + createFireRateString(Config.GrizzlyBear.ATTACK_DELAY) + NEW_LINE
                + createRangeString(Config.GrizzlyBear.RANGE) + NEW_LINE
                + createDamageString(Config.GrizzlyBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
                + Config.GrizzlyBear.INFO_TEXT);
/*
        map.put(BearryPotter.class, );
        map.put(SniperBear.class, );
        map.put(SovietBear.class, );
        map.put(Barbearian.class, );
        map.put(BearGrylls.class, Config.BearGrylls.COST);
        map.put(Beer.class, Config.Beer.COST);
        map.put(RubixCubeBear.class, Config.RubixCubeBear.COST);
        map.put(BazookaBear.class, Config.BazookaBear.COST);
*/
        return map;
    }

    private String createFireRateString(int attackDelay) {
        return "Attack Speed: " + String.format("%.2f", attackDelay / 60.0) + "/s";
    }

    private String createRangeString(double range) {
        return "Range: " + range + " tiles";
    }

    private String createDamageString(int damage) {
        return "Damage: " + damage;
    }

    public void draw(Graphics g, int panelWidth, int panelHeight) {
        int x = (int) (LEFT * panelWidth);
        int y = (int) (TOP * panelHeight);
        int width = (int) (WIDTH * panelWidth);
        int height = (int) (HEIGHT * panelHeight);
        int margin = (int) (0.01 * panelWidth);

        g.setColor(ColorHandler.INFO_PANEL_BACKGROUND);
        g.fillRect(x, y, width, height);


        infoText.setLocation(x + margin, y + margin);
        infoText.setFont(new Font("serif", Font.BOLD, (int) (width * 0.1)));
        infoText.setSize(width - 2 * margin, height - 2 * margin);
        infoText.setText("<html>" + towerInfoStringMap.get(controllerStateValues.getSelectedTowerType()) + "</html>");
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
