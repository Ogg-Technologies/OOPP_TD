package view;

import config.Config;

import java.awt.*;

/**
 * @author Sebastian, Erik
 * This class is for grouping and creating all the colors for view elements
 * Is used by layers in view
 */
public final class ColorHandler {

    private ColorHandler() {
    }

    public static final Color INVISIBLE = new Color(0, 0, 0, 0);
    public static final Color VALID_TILE_HOVER;
    public static final Color INVALID_TILE_HOVER;
    public static final Color PATH;
    public static final Color GROUND;
    public static final Color BACKGROUND;
    public static final Color STANDARD_GUI_BACKGROUND;
    public static final Color PLAYER_HEALTH;
    public static final Color ENEMY_HEALTH;
    public static final Color TOWER_PANEL;
    public static final Color TOWER_BUTTON_BACKGROUND;
    public static final Color TOWER_BUTTON_LABEL;
    public static final Color GHOST_RANGE;
    public static final Color GAME_BACKGROUND;
    public static final Color CLICKED_BUTTON_BORDER;
    public static final Color INFO_PANEL_BACKGROUND;


    static {
        VALID_TILE_HOVER = convert(Config.ColorCode.VALID_TILE_HOVER, Config.ColorCode.VALID_TILE_HOVER_ALPHA);
        INVALID_TILE_HOVER = convert(Config.ColorCode.INVALID_TILE_HOVER, Config.ColorCode.INVALID_TILE_HOVER_ALPHA);
        PATH = Color.decode(Config.ColorCode.PATH);
        GROUND = Color.decode(Config.ColorCode.GROUND);
        BACKGROUND = Color.decode(Config.ColorCode.BACKGROUND);
        STANDARD_GUI_BACKGROUND = Color.decode(Config.ColorCode.STANDARD_GUI_BACKGROUND);
        PLAYER_HEALTH = Color.decode(Config.ColorCode.PLAYER_HEALTH);
        ENEMY_HEALTH = Color.decode(Config.ColorCode.ENEMY_HEALTH);
        TOWER_PANEL = Color.decode(Config.ColorCode.TOWER_PANEL);
        TOWER_BUTTON_BACKGROUND = Color.decode(Config.ColorCode.TOWER_BUTTON_BACKGROUND);
        TOWER_BUTTON_LABEL = convert(Config.ColorCode.STANDARD_GUI_BACKGROUND, Config.ColorCode.TOWER_BUTTON_LABEL_ALPHA);
        GHOST_RANGE = convert(Config.ColorCode.GHOST_RANGE, Config.ColorCode.GHOST_RANGE_ALPHA);
        GAME_BACKGROUND = Color.decode(Config.ColorCode.GAME_BACKGROUND);
        CLICKED_BUTTON_BORDER = Color.decode(Config.ColorCode.CLICKED_BUTTON_BORDER);
        INFO_PANEL_BACKGROUND = Color.decode(Config.ColorCode.INFO_PANEL_BACKGROUND);
    }

    /**
     * Converts a hexCode and an alpha to a Color object
     *
     * @param hexCode the hexCode needs to start with '#' and have six hexadecimal values following the '#'
     * @param alpha   the alpha needs to be between 0-255
     * @return a color from the hexCode and alpha values
     */
    private static Color convert(String hexCode, int alpha) {
        if (hexCode.length() != 7 || !hexCode.startsWith("#")) {
            throw new IllegalArgumentException("The hexCode " + hexCode + " is not a valid hexCode");
        }

        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("The alpha value " + alpha + " is not in range 0-255");
        }

        int r, g, b;
        try {
            r = Integer.parseInt(hexCode.substring(1, 3), 16);
            g = Integer.parseInt(hexCode.substring(3, 5), 16);
            b = Integer.parseInt(hexCode.substring(5, 7), 16);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The hexCode " + hexCode + " is not a valid hexCode");
        }
        return new Color(r, g, b, alpha);
    }
}
