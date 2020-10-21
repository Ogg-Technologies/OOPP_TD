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
 */
public final class Config {

    private Config() {
    }

    private static final String PROPERTY_PATH = "src/main/resources/config.properties";
    private static final String IMAGE_ROOT = "src/main/resources/img/";

    private static final Properties prop = readPropertyFile();

    /**
     * This method creates the Properties object that takes care of the properties file.
     *
     * @return A properties object that takes care of the properties file
     */
    private static Properties readPropertyFile() {
        Properties prop;
        InputStream inputStream = null;
        try {
            prop = new Properties();
            inputStream = new FileInputStream(new File(PROPERTY_PATH));
            prop.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Property file could not be loaded");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    /**
     * This method is for reading a double value (or int if casted) from property file.
     *
     * @param propName The name of the key
     * @return the value from properties as a double
     */
    private static double readDoubleValue(String propName) {
        if (prop == null) {
            throw new NullPointerException("Properties object has not been created yet");
        }
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
     * This method is for reading a String value from the properties file.
     *
     * @param propName The name of the key
     * @return the value from properties as a String
     */
    private static String readStringValue(String propName) {
        if (prop == null) {
            throw new NullPointerException("Properties object has not been created yet");
        }
        String propVal = prop.getProperty(propName);
        if (propVal == null) {
            throw new NoSuchPropertyException(propName);
        }
        return propVal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int ROTATED_IMAGE_CACHE_SIZE = (int) readDoubleValue("rotated_image_cache_size");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Towers
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class BearGrylls {
        public static final int BUFF_PERCENTAGE = (int) readDoubleValue("bear_grylls_buff_percentage");
        public static final int BUFF_DURATION = (int) readDoubleValue("bear_grylls_buff_duration");
        public static final double RANGE = readDoubleValue("bear_grylls_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("bear_grylls_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("bear_grylls_cost");
    }

    public static class RubixCubeBear {
        public static final int BUFF_PERCENTAGE = (int) readDoubleValue("rubix_cube_bear_buff_percentage");
        public static final int BUFF_DURATION = (int) readDoubleValue("rubix_cube_bear_buff_duration");
        public static final double RANGE = readDoubleValue("rubix_cube_bear_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("rubix_cube_bear_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("rubix_cube_bear_cost");
    }

    public static class Beer {
        public static final int DAMAGE_BUFF_PERCENTAGE = (int) readDoubleValue("beer_damage_buff_percentage");
        public static final int RANGE_BUFF_PERCENTAGE = (int) readDoubleValue("beer_range_buff_percentage");
        public static final int FIRE_RATE_BUFF_PERCENTAGE = (int) readDoubleValue("beer_fire_rate_buff_percentage");
        public static final int BUFF_DURATION = (int) readDoubleValue("beer_buff_duration");
        public static final double RANGE = readDoubleValue("beer_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("beer_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("beer_cost");
    }

    public static class GrizzlyBear {
        public static final int BASE_DAMAGE = (int) readDoubleValue("grizzly_bear_base_damage");
        public static final double RANGE = readDoubleValue("grizzly_bear_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("grizzly_bear_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("grizzly_bear_cost");
    }

    public static class BearryPotter {
        public static final int BASE_DAMAGE = (int) readDoubleValue("bearry_potter_base_damage");
        public static final double RANGE = readDoubleValue("bearry_potter_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("bearry_potter_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("bearry_potter_cost");
    }

    public static class SniperBear {
        public static final int BASE_DAMAGE = (int) readDoubleValue("sniper_bear_base_damage");
        public static final double RANGE = readDoubleValue("sniper_bear_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("sniper_bear_updates_between_attacks");
        public static final int COST = (int) readDoubleValue("sniper_bear_cost");
    }

    public static class SovietBear {
        public static final int BASE_DAMAGE = (int) readDoubleValue("soviet_bear_base_damage");
        public static final double RANGE = readDoubleValue("soviet_bear_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("soviet_bear_updates_between_attacks");
        public static final int ATTACKS_PER_BURST = (int) readDoubleValue("soviet_bear_attacks_per_burst");
        public static final int COST = (int) readDoubleValue("soviet_bear_cost");
        public static final int TICKS_BETWEEN_ATTACKS = (int) readDoubleValue("soviet_bear_ticks_between_attack");
    }

    public static class Barbearian {
        public static final int BASE_DAMAGE = (int) readDoubleValue("barbearian_base_damage");
        public static final double RANGE = readDoubleValue("barbearian_range");
        public static final int ATTACK_DELAY = (int) readDoubleValue("barbearian_updates_between_attacks");
        public static final int ATTACKS_PER_BURST = (int) readDoubleValue("barbearian_attacks_per_burst");
        public static final int COST = (int) readDoubleValue("barbearian_cost");
        public static final int TICKS_BETWEEN_ATTACKS = (int) readDoubleValue("barbearian_ticks_between_attack");
    }

    public static class BazookaBear {
        public static final int BASE_DAMAGE = (int) readDoubleValue("bazooka_bear_base_damage");
        public static final double RANGE = readDoubleValue("bazooka_bear_range");
        public static final int COST = (int) readDoubleValue("bazooka_bear_cost");
        public static final int ATTACK_DELAY = (int) readDoubleValue("bazooka_bear_updates_between_attacks");
    }

    public static class Baeron {
        public static final double RANGE = readDoubleValue("bearon_range");
        public static final int COINS_PER_TOWER = (int) readDoubleValue("bearon_coins_per_tower");
        public static final int ATTACK_DELAY = (int) readDoubleValue("bearon_updates_between_attacks");

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Projectiles
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Rock {
        public static final double SPEED = readDoubleValue("rock_speed");
        public static final double SIZE = readDoubleValue("rock_size");
    }

    public static class BombardaCharm {
        public static final double SPEED = readDoubleValue("bombarda_charm_speed");
        public static final double SIZE = readDoubleValue("bombarda_charm_size");
        public static final double EXPLOSION_RADIUS = readDoubleValue("bombarda_explosion_radius");
    }

    public static class Rocket {
        public static final double SPEED = readDoubleValue("rocket_speed");
        public static final double SIZE = readDoubleValue("rocket_size");
        public static final double EXPLOSION_RADIUS = readDoubleValue("rocket_explosion_radius");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Enemies
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Fishstick {
        public static final double SPEED = readDoubleValue("fishstick_speed");
        public static final double HEALTH = readDoubleValue("fishstick_health");
        public static final int VALUE = (int) readDoubleValue("fishstick_value");
    }

    public static class Swordfish {
        public static final double SPEED = readDoubleValue("swordfish_speed");
        public static final double HEALTH = readDoubleValue("swordfish_health");
        public static final int VALUE = (int) readDoubleValue("swordfish_value");
    }

    public static class FishAndChips {
        public static final double SPEED = readDoubleValue("fish_and_chips_speed");
        public static final double HEALTH = readDoubleValue("fish_and_chips_health");
        public static final int VALUE = (int) readDoubleValue("fish_and_chips_value");
    }

    public static class FishInABoat {
        public static final double SPEED = readDoubleValue("fish_in_a_boat_speed");
        public static final double HEALTH = readDoubleValue("fish_in_a_boat_health");
        public static final int VALUE = (int) readDoubleValue("fish_in_a_boat_value");
    }

    public static class Sailfish {
        public static final double SPEED = readDoubleValue("sailfish_speed");
        public static final double HEALTH = readDoubleValue("sailfish_health");
        public static final int VALUE = (int) readDoubleValue("sailfish_value");
    }

    public static class Shark {
        public static final double SPEED = readDoubleValue("shark_speed");
        public static final double HEALTH = readDoubleValue("shark_health");
        public static final int VALUE = (int) readDoubleValue("shark_value");
    }

    public static class FishInAFishTank {
        public static final double SPEED = readDoubleValue("fish_in_a_fish_tank_speed");
        public static final double HEALTH = readDoubleValue("fish_in_a_fish_tank_health");
        public static final int VALUE = (int) readDoubleValue("fish_in_a_fish_tank_value");
    }

    public static class TankSinatra {
        public static final double SPEED = readDoubleValue("tank_sinatra_speed");
        public static final double HEALTH = readDoubleValue("tank_sinatra_health");
        public static final int VALUE = (int) readDoubleValue("tank_sinatra_value");
    }

    public static class FlyingFish {
        public static final double SPEED = readDoubleValue("flying_fish_speed");
        public static final double HEALTH = readDoubleValue("flying_fish_health");
        public static final int VALUE = (int) readDoubleValue("flying_fish_value");
        public static final int FLY_AMOUNT = (int) readDoubleValue("flying_fish_fly_amount");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Other
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Player {
        public static final int START_MONEY = (int) readDoubleValue("player_start_money");
        public static final int START_HEALTH = (int) readDoubleValue("player_start_health");
    }

    public static class ColorCode {
        public static final String VALID_TILE_HOVER = readStringValue("color_valid_tile_hover");
        public static final int VALID_TILE_HOVER_ALPHA = (int) readDoubleValue("color_valid_tile_hover_alpha");
        public static final String INVALID_TILE_HOVER = readStringValue("color_invalid_tile_hover");
        public static final int INVALID_TILE_HOVER_ALPHA = (int) readDoubleValue("color_invalid_tile_hover_alpha");
        public static final String PATH = readStringValue("color_path");
        public static final String GROUND = readStringValue("color_ground");
        public static final String BACKGROUND = readStringValue("color_background");
        public static final String STANDARD_GUI_BACKGROUND = readStringValue("color_standard_gui_background");
        public static final String PLAYER_HEALTH = readStringValue("color_player_health");
        public static final String ENEMY_HEALTH = readStringValue("color_enemy_health");
        public static final String TOWER_PANEL = readStringValue("color_tower_panel");
        public static final String TOWER_BUTTON_BACKGROUND = readStringValue("color_tower_button_background");
        public static final int TOWER_BUTTON_LABEL_ALPHA = (int) readDoubleValue("color_tower_button_label_alpha");
        public static final String GHOST_RANGE = readStringValue("color_ghost_range");
        public static final int GHOST_RANGE_ALPHA = (int) readDoubleValue("color_ghost_range_alpha");
        public static final String GAME_BACKGROUND = readStringValue("color_game_background");
        public static final String CLICKED_BUTTON_BORDER = readStringValue("color_clicked_button_border");
    }

    public static class ImagePath {
        public static final String ARROW_LEFT = IMAGE_ROOT + readStringValue("img_arrow_left");
        public static final String ARROW_RIGHT = IMAGE_ROOT + readStringValue("img_arrow_right");
        public static final String START_SCREEN = IMAGE_ROOT + readStringValue("img_background");
        public static final String AXE = IMAGE_ROOT + readStringValue("img_axe");
        public static final String GRIZZLY_BEAR = IMAGE_ROOT + readStringValue("img_grizzly_bear");
        public static final String BEARRY_POTTER = IMAGE_ROOT + readStringValue("img_bearry_potter");
        public static final String SNIPER_BEAR = IMAGE_ROOT + readStringValue("img_sniper_bear");
        public static final String SOVIET_BEAR = IMAGE_ROOT + readStringValue("img_soviet_bear");
        public static final String BARBEARIAN = IMAGE_ROOT + readStringValue("img_barbearian");
        public static final String BEAR_GRYLLS = IMAGE_ROOT + readStringValue("img_beargrylls");
        public static final String ROCK = IMAGE_ROOT + readStringValue("img_stone");
        public static final String BOMBARDA_CHARM = IMAGE_ROOT + readStringValue("img_bombarda_charm");
        public static final String SMOKE = IMAGE_ROOT + readStringValue("img_smoke");
        public static final String BULLET = IMAGE_ROOT + readStringValue("img_bullet");
        public static final String FISHSTICK = IMAGE_ROOT + readStringValue("img_fishstick");
        public static final String SWORDFISH = IMAGE_ROOT + readStringValue("img_swordfish");
        public static final String FISH_AND_CHIPS = IMAGE_ROOT + readStringValue("img_fish_and_chips");
        public static final String FISH_IN_A_BOAT = IMAGE_ROOT + readStringValue("img_fish_in_a_boat");
        public static final String SAILFISH = IMAGE_ROOT + readStringValue("img_sailfish");
        public static final String SHARK = IMAGE_ROOT + readStringValue("img_shark");
        public static final String FISH_IN_A_FISH_TANK = IMAGE_ROOT + readStringValue("img_fish_in_a_fish_tank");
        public static final String TANK_SINATRA = IMAGE_ROOT + readStringValue("img_tank_sinatra");
        public static final String FLYING_FISH = IMAGE_ROOT + readStringValue("img_flying_fish");
        public static final String BASE = IMAGE_ROOT + readStringValue("img_base");
        public static final String COMPASS = IMAGE_ROOT + readStringValue("img_compass");
        public static final String BEER_BEAR = IMAGE_ROOT + readStringValue("img_beerBear");
        public static final String BEER = IMAGE_ROOT + readStringValue("img_beer");
        public static final String RUBIX_CUBE = IMAGE_ROOT + readStringValue("img_rubixCube");
        public static final String RUBIX_CUBE_BEAR = IMAGE_ROOT + readStringValue("img_rubixCubeBear");
        public static final String BAZOOKA_BEAR = IMAGE_ROOT + readStringValue("img_bazookaBear");
        public static final String EXPLOSION = IMAGE_ROOT + readStringValue("img_explosion");
        public static final String ROCKET = IMAGE_ROOT + readStringValue("img_rocket");
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
