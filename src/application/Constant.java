package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sebastian, Erik
 * This class stores all the values from a config file, and also fetch all the data from the same config file.
 * This is used in a lot of classes that need constants, for example the economy class with the cost of towers,
 * and values of enemies.
 * This class also has a lot of nested classes, to categorize the stored values.
 * This class is also a Singleton, because no more copies than one will ever be needed.
 */
public final class Constant {

    private static final String propertyPath = "resource/config.properties";

    private static Constant instance;

    public final int ROTATED_IMAGE_CACHE_SIZE;
    public final Player PLAYER;
    public final TowerDamage TOWER_DAMAGE;
    public final TowerRange TOWER_RANGE;
    public final TowerUpdatesBetweenAttacks TOWER_ATTACK_DELAY;
    public final TowerPrice TOWER_PRICE;
    public final ProjectileSpeed PROJECTILE_SPEED;
    public final ProjectileSize PROJECTILE_SIZE;
    public final EnemyDeathReward ENEMY_DEATH_REWARD;
    public final EnemySpeed ENEMY_SPEED;
    public final EnemyHealth ENEMY_HEALTH;
    public final ColorCode COLOR_CODE;
    public final ImagePath IMAGE_PATH;

    /**
     * Sets up a Properties object for handling the properties file, and also assign all the data to the proper
     * nested class.
     */
    private Constant() {
        Properties prop;
        try {
            prop = readPropertyFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ROTATED_IMAGE_CACHE_SIZE = (int) readDoubleValue(prop, "rotated_image_cache_size");
        TOWER_PRICE = new TowerPrice(prop);
        TOWER_DAMAGE = new TowerDamage(prop);
        TOWER_RANGE = new TowerRange(prop);
        ENEMY_DEATH_REWARD = new EnemyDeathReward(prop);
        ENEMY_SPEED = new EnemySpeed(prop);
        ENEMY_HEALTH = new EnemyHealth(prop);
        PLAYER = new Player(prop);
        PROJECTILE_SPEED = new ProjectileSpeed(prop);
        PROJECTILE_SIZE = new ProjectileSize(prop);
        TOWER_ATTACK_DELAY = new TowerUpdatesBetweenAttacks(prop);
        COLOR_CODE = new ColorCode(prop);
        IMAGE_PATH = new ImagePath(prop);
    }

    /**
     * Gets the only instance of this class
     *
     * @return a singleton of this class
     */
    public static Constant getInstance() {
        if (instance == null) {
            instance = new Constant();
        }
        return instance;
    }


    /**
     * This method creates the Properties object that takes care of the properties file.
     *
     * @return A properties object that takes care of the properties file
     * @throws IOException if the fileInputStream cannot be closed
     */
    private Properties readPropertyFile() throws IOException {
        Properties prop;
        InputStream inputStream = null;
        try {
            prop = new Properties();
            inputStream = new FileInputStream(new File(propertyPath));
            prop.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Property file could not be loaded");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return prop;
    }

    /**
     * This method is for reading a double value (or int if casted) from property.
     *
     * @param prop     The Properties class, that takes care of reading from the properties file.
     * @param propName The name of the key
     * @return the value from properties as a double
     */
    private static double readDoubleValue(Properties prop, String propName) {
        String propVal = prop.getProperty(propName);
        if (propVal == null) {
            throw new NoSuchPropertyException(propName);
        }
        try {
            return Double.parseDouble(prop.getProperty(propName));
        } catch (NumberFormatException e) {
            throw new RuntimeException("The Property " + propName + " is not a double");
        }
    }

    /**
     * This method is for reading a String value.
     *
     * @param prop     The Properties class, that takes care of reading from the properties file.
     * @param propName The name of the key
     * @return the value from properties as a String
     */
    private static String readStringValue(Properties prop, String propName) {
        String propVal = prop.getProperty(propName);
        if (propVal == null) {
            throw new NoSuchPropertyException(propName);
        }
        return propVal;
    }

    public static class Player {
        public final int START_MONEY;
        public final int START_HEALTH;

        private Player(Properties prop) {
            START_MONEY = (int) readDoubleValue(prop, "player_start_money");
            START_HEALTH = (int) readDoubleValue(prop, "player_start_health");
        }
    }

    public static class TowerDamage {
        public final int BEARRY_POTTER;
        public final int GRIZZLY_BEAR;
        public final int SNIPER_BEAR;

        private TowerDamage(Properties prop) {
            BEARRY_POTTER = (int) readDoubleValue(prop, "bearry_potter_base_damage");
            GRIZZLY_BEAR = (int) readDoubleValue(prop, "grizzly_bear_base_damage");
            SNIPER_BEAR = (int) readDoubleValue(prop, "sniper_bear_base_damage");
        }
    }

    public static class TowerRange {
        public final double BEARRY_POTTER;
        public final double GRIZZLY_BEAR;
        public final double SNIPER_BEAR;

        private TowerRange(Properties prop) {
            BEARRY_POTTER = readDoubleValue(prop, "bearry_potter_range");
            GRIZZLY_BEAR = readDoubleValue(prop, "grizzly_bear_range");
            SNIPER_BEAR = readDoubleValue(prop, "sniper_bear_range");
        }
    }

    public static class TowerUpdatesBetweenAttacks {
        public final int BEARRY_POTTER;
        public final int GRIZZLY_BEAR;
        public final int SNIPER_BEAR;

        private TowerUpdatesBetweenAttacks(Properties prop) {
            BEARRY_POTTER = (int) readDoubleValue(prop, "bearry_potter_updates_between_attacks");
            GRIZZLY_BEAR = (int) readDoubleValue(prop, "grizzly_bear_updates_between_attacks");
            SNIPER_BEAR = (int) readDoubleValue(prop, "sniper_bear_updates_between_attacks");
        }
    }

    public static class TowerPrice {
        public final int BEARRY_POTTER;
        public final int GRIZZLY_BEAR;
        public final int SNIPER_BEAR;
        public final int SOVIET_BEAR;
        public final int BARBEARIAN;

        private TowerPrice(Properties prop) {
            BEARRY_POTTER = (int) readDoubleValue(prop, "bearry_potter_cost");
            GRIZZLY_BEAR = (int) readDoubleValue(prop, "grizzly_bear_cost");
            SNIPER_BEAR = (int) readDoubleValue(prop, "sniper_bear_cost");
            SOVIET_BEAR = (int) readDoubleValue(prop, "soviet_bear_cost");
            BARBEARIAN = (int) readDoubleValue(prop, "barbearian_cost");
        }
    }

    public static class ProjectileSpeed {
        public final double ROCK;
        public final double BOMBARDA_CHARM;

        private ProjectileSpeed(Properties prop) {
            ROCK = readDoubleValue(prop, "rock_speed");
            BOMBARDA_CHARM = readDoubleValue(prop, "bombarda_charm_speed");
        }
    }

    public static class ProjectileSize {
        public final double ROCK;
        public final double BOMBARDA_CHARM;

        private ProjectileSize(Properties prop) {
            ROCK = readDoubleValue(prop, "rock_size");
            BOMBARDA_CHARM = readDoubleValue(prop, "bombarda_charm_size");
        }
    }

    public static class EnemyDeathReward {
        public final int FISHSTICK;
        public final int SWORDFISH;
        public final int FISH_AND_CHIPS;
        public final int FISH_IN_A_BOAT;
        public final int SAILFISH;
        public final int SHARK;
        public final int FISH_IN_A_FISH_TANK;

        private EnemyDeathReward(Properties prop) {
            FISHSTICK = (int) readDoubleValue(prop, "fishstick_value");
            SWORDFISH = (int) readDoubleValue(prop, "swordfish_value");
            FISH_AND_CHIPS = (int) readDoubleValue(prop, "fish_and_chips_value");
            FISH_IN_A_BOAT = (int) readDoubleValue(prop, "fish_in_a_boat_value");
            SAILFISH = (int) readDoubleValue(prop, "sailfish_value");
            SHARK = (int) readDoubleValue(prop, "shark_value");
            FISH_IN_A_FISH_TANK = (int) readDoubleValue(prop, "fish_in_a_fish_tank_value");
        }
    }

    public static class EnemySpeed {

        public final double FISHSTICK;
        public final double SWORDFISH;
        public final double FISH_AND_CHIPS;
        public final double FISH_IN_A_BOAT;
        public final double SAILFISH;
        public final double SHARK;
        public final double FISH_IN_A_FISH_TANK;

        private EnemySpeed(Properties prop) {
            FISHSTICK = readDoubleValue(prop, "fishstick_speed");
            SWORDFISH = readDoubleValue(prop, "swordfish_speed");
            FISH_AND_CHIPS = readDoubleValue(prop, "fish_and_chips_speed");
            FISH_IN_A_BOAT = readDoubleValue(prop, "fish_in_a_boat_speed");
            SAILFISH = readDoubleValue(prop, "sailfish_speed");
            SHARK = readDoubleValue(prop, "shark_speed");
            FISH_IN_A_FISH_TANK = readDoubleValue(prop, "fish_in_a_fish_tank_speed");
        }
    }

    public static class EnemyHealth {

        public final double FISHSTICK;
        public final double SWORDFISH;
        public final double FISH_AND_CHIPS;
        public final double FISH_IN_A_BOAT;
        public final double SAILFISH;
        public final double SHARK;
        public final double FISH_IN_A_FISH_TANK;

        private EnemyHealth(Properties prop) {
            FISHSTICK = readDoubleValue(prop, "fishstick_health");
            SWORDFISH = readDoubleValue(prop, "swordfish_health");
            FISH_AND_CHIPS = readDoubleValue(prop, "fish_and_chips_health");
            FISH_IN_A_BOAT = readDoubleValue(prop, "fish_in_a_boat_health");
            SAILFISH = readDoubleValue(prop, "sailfish_health");
            SHARK = readDoubleValue(prop, "shark_health");
            FISH_IN_A_FISH_TANK = readDoubleValue(prop, "fish_in_a_fish_tank_health");
        }
    }

    public static class ColorCode {
        public final String VALID_TILE_HOVER;
        public final int VALID_TILE_HOVER_ALPHA;
        public final String INVALID_TILE_HOVER;
        public final int INVALID_TILE_HOVER_ALPHA;
        public final String PATH;
        public final String GROUND;
        public final String BACKGROUND;
        public final String STANDARD_GUI_BACKGROUND;
        public final String PLAYER_HEALTH;
        public final String ENEMY_HEALTH;
        public final String TOWER_PANEL;
        public final String TOWER_BUTTON_BACKGROUND;
        public final int TOWER_BUTTON_LABEL_ALPHA;

        private ColorCode(Properties prop) {
            VALID_TILE_HOVER = readStringValue(prop, "color_valid_tile_hover");
            VALID_TILE_HOVER_ALPHA = (int) readDoubleValue(prop, "color_valid_tile_hover_alpha");
            INVALID_TILE_HOVER = readStringValue(prop, "color_invalid_tile_hover");
            INVALID_TILE_HOVER_ALPHA = (int) readDoubleValue(prop, "color_invalid_tile_hover_alpha");
            PATH = readStringValue(prop, "color_path");
            GROUND = readStringValue(prop, "color_ground");
            BACKGROUND = readStringValue(prop, "color_background");
            STANDARD_GUI_BACKGROUND = readStringValue(prop, "color_standard_gui_background");
            PLAYER_HEALTH = readStringValue(prop, "color_player_health");
            ENEMY_HEALTH = readStringValue(prop, "color_enemy_health");
            TOWER_PANEL = readStringValue(prop, "color_tower_panel");
            TOWER_BUTTON_BACKGROUND = readStringValue(prop, "color_tower_button_background");
            TOWER_BUTTON_LABEL_ALPHA = (int) readDoubleValue(prop, "color_tower_button_label_alpha");
        }
    }

    public static class ImagePath {

        public final String GRIZZLY_BEAR;
        public final String BEARRY_POTTER;
        public final String SNIPER_BEAR;
        public final String ROCK;
        public final String BOMBARDA_CHARM;
        public final String SMOKE;
        public final String FISHSTICK;
        public final String BASE;

        private ImagePath(Properties prop) {
            GRIZZLY_BEAR = readStringValue(prop, "grizzly_bear_path");
            BEARRY_POTTER = readStringValue(prop, "bearry_potter_path");
            SNIPER_BEAR = readStringValue(prop, "sniper_bear_path");
            ROCK = readStringValue(prop, "stone_path");
            BOMBARDA_CHARM = readStringValue(prop, "bombarda_charm_path");
            SMOKE = readStringValue(prop, "smoke_path");
            FISHSTICK = readStringValue(prop, "fishstick_path");
            BASE = readStringValue(prop, "base_path");
        }
    }

    /**
     * An Exception for the case where the property is not defined in the properties file.
     */
    private static class NoSuchPropertyException extends RuntimeException {
        public NoSuchPropertyException(String propName) {
            super("The property " + propName + " was not found in config file");
        }
    }
}
