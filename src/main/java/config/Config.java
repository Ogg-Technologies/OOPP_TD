package config;

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
public enum  Config {
    INSTANCE;

    private static final String propertyPath = "src/main/resources/config.properties";

    public final int ROTATED_IMAGE_CACHE_SIZE;
    public final Player PLAYER;
    public final BearGrylls BEAR_GRYLLS;
    public final RubixCubeBear RUBIX_CUBE_BEAR;
    public final Beer BEER;
    public final GrizzlyBear GRIZZLY_BEAR;
    public final BearryPotter BEARRY_POTTER;
    public final SniperBear SNIPER_BEAR;
    public final SovietBear SOVIET_BEAR;
    public final Barbearian BARBEARIAN;
    public final BazookaBear BAZOOKABEAR;
    public final Rock ROCK;
    public final BombardaCharm BOMBARDA_CHARM;
    public final Rocket ROCKET;
    public final Fishstick FISHSTICK;
    public final Swordfish SWORDFISH;
    public final FishAndChips FISH_AND_CHIPS;
    public final FishInABoat FISH_IN_A_BOAT;
    public final Sailfish SAILFISH;
    public final Shark SHARK;
    public final FishInAFishTank FISH_IN_A_FISH_TANK;
    public final FlyingFish FLYING_FISH;
    public final ColorCode COLOR_CODE;
    public final ImagePath IMAGE_PATH;

    /**
     * Sets up a Properties object for handling the properties file, and also assign all the data to the proper
     * nested class.
     */
    Config() {
        Properties prop;
        try {
            prop = readPropertyFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ROTATED_IMAGE_CACHE_SIZE = (int) readDoubleValue(prop, "rotated_image_cache_size");
        PLAYER = new Player(prop);
        BEAR_GRYLLS = new BearGrylls(prop);
        RUBIX_CUBE_BEAR = new RubixCubeBear(prop);
        BEER = new Beer(prop);
        GRIZZLY_BEAR = new GrizzlyBear(prop);
        BEARRY_POTTER = new BearryPotter(prop);
        SNIPER_BEAR = new SniperBear(prop);
        SOVIET_BEAR = new SovietBear(prop);
        BARBEARIAN = new Barbearian(prop);
        BAZOOKABEAR = new BazookaBear(prop);
        ROCK = new Rock(prop);
        BOMBARDA_CHARM = new BombardaCharm(prop);
        ROCKET = new Rocket(prop);
        FISHSTICK = new Fishstick(prop);
        SWORDFISH = new Swordfish(prop);
        FISH_AND_CHIPS = new FishAndChips(prop);
        FISH_IN_A_BOAT = new FishInABoat(prop);
        SAILFISH = new Sailfish(prop);
        SHARK = new Shark(prop);
        FISH_IN_A_FISH_TANK = new FishInAFishTank(prop);
        FLYING_FISH = new FlyingFish(prop);
        COLOR_CODE = new ColorCode(prop);
        IMAGE_PATH = new ImagePath(prop);
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

    public static class BearGrylls {
        public final int BUFF_PERCENTAGE;
        public final int BUFF_DURATION;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private BearGrylls(Properties prop) {
            BUFF_PERCENTAGE = (int) readDoubleValue(prop, "bear_grylls_buff_percentage");
            BUFF_DURATION = (int) readDoubleValue(prop, "bear_grylls_buff_duration");
            RANGE = readDoubleValue(prop, "bear_grylls_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "bear_grylls_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "bear_grylls_cost");
        }
    }

    public static class RubixCubeBear {
        public final int BUFF_PERCENTAGE;
        public final int BUFF_DURATION;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private RubixCubeBear(Properties prop) {
            BUFF_PERCENTAGE = (int) readDoubleValue(prop, "rubix_cube_bear_buff_percentage");
            BUFF_DURATION = (int) readDoubleValue(prop, "rubix_cube_bear_buff_duration");
            RANGE = readDoubleValue(prop, "rubix_cube_bear_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "rubix_cube_bear_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "rubix_cube_bear_cost");
        }
    }

    public static class Beer {
        public final int DAMAGE_BUFF_PERCENTAGE;
        public final int RANGE_BUFF_PERCENTAGE;
        public final int FIRE_RATE_BUFF_PERCENTAGE;
        public final int BUFF_DURATION;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private Beer(Properties prop) {
            DAMAGE_BUFF_PERCENTAGE = (int) readDoubleValue(prop, "beer_damage_buff_percentage");
            RANGE_BUFF_PERCENTAGE = (int) readDoubleValue(prop, "beer_range_buff_percentage");
            FIRE_RATE_BUFF_PERCENTAGE = (int) readDoubleValue(prop, "beer_fire_rate_buff_percentage");
            BUFF_DURATION = (int) readDoubleValue(prop, "beer_buff_duration");
            RANGE = readDoubleValue(prop, "beer_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "beer_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "beer_cost");
        }
    }

    public static class GrizzlyBear {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private GrizzlyBear(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "grizzly_bear_base_damage");
            RANGE = readDoubleValue(prop, "grizzly_bear_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "grizzly_bear_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "grizzly_bear_cost");
        }
    }

    public static class BearryPotter {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private BearryPotter(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "bearry_potter_base_damage");
            RANGE = readDoubleValue(prop, "bearry_potter_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "bearry_potter_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "bearry_potter_cost");
        }
    }

    public static class SniperBear {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private SniperBear(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "sniper_bear_base_damage");
            RANGE = readDoubleValue(prop, "sniper_bear_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "sniper_bear_updates_between_attacks");
            COST = (int) readDoubleValue(prop, "sniper_bear_cost");
        }
    }

    public static class SovietBear {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int ATTACKS_PER_BURST;
        public final int TICKS_BETWEEN_ATTACKS;
        public final int COST;


        private SovietBear(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "soviet_bear_base_damage");
            RANGE = readDoubleValue(prop, "soviet_bear_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "soviet_bear_updates_between_attacks");
            ATTACKS_PER_BURST = (int) readDoubleValue(prop, "soviet_bear_attacks_per_burst");
            COST = (int) readDoubleValue(prop, "soviet_bear_cost");
            TICKS_BETWEEN_ATTACKS = (int) readDoubleValue(prop, "soviet_bear_ticks_between_attack");
        }
    }

    public static class Barbearian {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int ATTACKS_PER_BURST;
        public final int TICKS_BETWEEN_ATTACKS;
        public final int COST;


        private Barbearian(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "barbearian_base_damage");
            RANGE = readDoubleValue(prop, "barbearian_range");
            ATTACK_DELAY = (int) readDoubleValue(prop, "barbearian_updates_between_attacks");
            ATTACKS_PER_BURST = (int) readDoubleValue(prop, "barbearian_attacks_per_burst");
            COST = (int) readDoubleValue(prop, "barbearian_cost");
            TICKS_BETWEEN_ATTACKS = (int) readDoubleValue(prop, "barbearian_ticks_between_attack");

        }
    }

    public static class BazookaBear {
        public final int BASE_DAMAGE;
        public final double RANGE;
        public final int ATTACK_DELAY;
        public final int COST;


        private BazookaBear(Properties prop) {
            BASE_DAMAGE = (int) readDoubleValue(prop, "bazooka_bear_base_damage");
            RANGE = readDoubleValue(prop, "bazooka_bear_range");
            COST = (int) readDoubleValue(prop, "bazooka_bear_cost");
            ATTACK_DELAY = (int) readDoubleValue(prop, "bazooka_bear_updates_between_attacks");

        }
    }

    public static class Rock {
        public final double SPEED;
        public final double SIZE;

        private Rock(Properties prop) {
            SPEED = readDoubleValue(prop, "rock_speed");
            SIZE = readDoubleValue(prop, "rock_size");
        }
    }

    public static class BombardaCharm {
        public final double SPEED;
        public final double SIZE;
        public final double EXPLOSION_RADIUS;

        private BombardaCharm(Properties prop) {
            SPEED = readDoubleValue(prop, "bombarda_charm_speed");
            SIZE = readDoubleValue(prop, "bombarda_charm_size");
            EXPLOSION_RADIUS = readDoubleValue(prop, "bombarda_explosion_radius");
        }
    }

    public static class Rocket {
        public final double SPEED;

        private Rocket(Properties prop) {
            SPEED = readDoubleValue(prop, "rocket_speed");
        }
    }


    public static class Fishstick {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private Fishstick(Properties prop) {
            SPEED = readDoubleValue(prop, "fishstick_speed");
            HEALTH = readDoubleValue(prop, "fishstick_health");
            VALUE = (int) readDoubleValue(prop, "fishstick_value");
        }
    }

    public static class Swordfish {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private Swordfish(Properties prop) {
            SPEED = readDoubleValue(prop, "swordfish_speed");
            HEALTH = readDoubleValue(prop, "swordfish_health");
            VALUE = (int) readDoubleValue(prop, "swordfish_value");
        }
    }

    public static class FishAndChips {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private FishAndChips(Properties prop) {
            SPEED = readDoubleValue(prop, "fish_and_chips_speed");
            HEALTH = readDoubleValue(prop, "fish_and_chips_health");
            VALUE = (int) readDoubleValue(prop, "fish_and_chips_value");
        }
    }

    public static class FishInABoat {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private FishInABoat(Properties prop) {
            SPEED = readDoubleValue(prop, "fish_in_a_boat_speed");
            HEALTH = readDoubleValue(prop, "fish_in_a_boat_health");
            VALUE = (int) readDoubleValue(prop, "fish_in_a_boat_value");
        }
    }

    public static class Sailfish {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private Sailfish(Properties prop) {
            SPEED = readDoubleValue(prop, "sailfish_speed");
            HEALTH = readDoubleValue(prop, "sailfish_health");
            VALUE = (int) readDoubleValue(prop, "sailfish_value");
        }
    }

    public static class Shark {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private Shark(Properties prop) {
            SPEED = readDoubleValue(prop, "shark_speed");
            HEALTH = readDoubleValue(prop, "shark_health");
            VALUE = (int) readDoubleValue(prop, "shark_value");
        }
    }

    public static class FishInAFishTank {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;

        private FishInAFishTank(Properties prop) {
            SPEED = readDoubleValue(prop, "fish_in_a_fish_tank_speed");
            HEALTH = readDoubleValue(prop, "fish_in_a_fish_tank_health");
            VALUE = (int) readDoubleValue(prop, "fish_in_a_fish_tank_value");
        }
    }

    public static class FlyingFish {
        public final double SPEED;
        public final double HEALTH;
        public final int VALUE;
        public final int FLY_AMOUNT;

        private FlyingFish(Properties prop) {
            SPEED = readDoubleValue(prop, "flying_fish_speed");
            HEALTH = readDoubleValue(prop, "flying_fish_health");
            VALUE = (int) readDoubleValue(prop, "flying_fish_value");
            FLY_AMOUNT = (int) readDoubleValue(prop, "flying_fish_fly_amount");
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
        public final String GHOST_RANGE;
        public final int GHOST_RANGE_ALPHA;
        public final String GAME_BACKGROUND;

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
            GHOST_RANGE = readStringValue(prop, "color_ghost_range");
            GHOST_RANGE_ALPHA = (int) readDoubleValue(prop, "color_ghost_range_alpha");
            GAME_BACKGROUND = readStringValue(prop, "color_game_background");
        }
    }

    public static class ImagePath {


        public final String BEER_BEAR;
        public final String BEER;
        public final String RUBIX_CUBE;
        public final String RUBIX_CUBE_BEAR;
        public final String COMPASS;
        public final String AXE;
        public final String GRIZZLY_BEAR;
        public final String BEAR_GRYLLS;
        public final String BEARRY_POTTER;
        public final String SNIPER_BEAR;
        public final String SOVIET_BEAR;
        public final String BARBEARIAN;
        public final String ROCK;
        public final String BOMBARDA_CHARM;
        public final String SMOKE;
        public final String BULLET;
        public final String FISHSTICK;
        public final String SWORDFISH;
        public final String FISH_AND_CHIPS;
        public final String FISH_IN_A_BOAT;
        public final String SAILFISH;
        public final String SHARK;
        public final String FISH_IN_A_FISH_TANK;
        public final String FLYING_FISH;
        public final String BASE;

        private ImagePath(Properties prop) {
            AXE = readStringValue(prop, "axe_path");
            GRIZZLY_BEAR = readStringValue(prop, "grizzly_bear_path");
            BEARRY_POTTER = readStringValue(prop, "bearry_potter_path");
            SNIPER_BEAR = readStringValue(prop, "sniper_bear_path");
            SOVIET_BEAR = readStringValue(prop, "soviet_bear_path");
            BARBEARIAN = readStringValue(prop, "barbearian_path");
            BEAR_GRYLLS = readStringValue(prop, "beargrylls_path");
            ROCK = readStringValue(prop, "stone_path");
            BOMBARDA_CHARM = readStringValue(prop, "bombarda_charm_path");
            SMOKE = readStringValue(prop, "smoke_path");
            BULLET = readStringValue(prop, "bullet_path");
            FISHSTICK = readStringValue(prop, "fishstick_path");
            SWORDFISH = readStringValue(prop, "swordfish_path");
            FISH_AND_CHIPS = readStringValue(prop, "fish_and_chips_path");
            FISH_IN_A_BOAT = readStringValue(prop, "fish_in_a_boat_path");
            SAILFISH = readStringValue(prop, "sailfish_path");
            SHARK = readStringValue(prop, "shark_path");
            FISH_IN_A_FISH_TANK = readStringValue(prop, "fish_in_a_fish_tank_path");
            FLYING_FISH = readStringValue(prop, "flying_fish_path");
            BASE = readStringValue(prop, "base_path");
            COMPASS = readStringValue(prop, "compass_path");
            BEER_BEAR = readStringValue(prop, "beerBear_path");
            BEER = readStringValue(prop, "beer_path");
            RUBIX_CUBE = readStringValue(prop, "rubixCube_path");
            RUBIX_CUBE_BEAR = readStringValue(prop, "rubixCubeBear_path");
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
