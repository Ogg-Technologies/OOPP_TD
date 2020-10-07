package application;

import java.io.*;
import java.util.Properties;

/**
 * @author Sebastian, Erik
 * This class stores all the values from a config file, and also fetch all the data from the same config file.
 * This is used in a lot of classes that need constants, for example the economy class with the cost of towers,
 * and values of enemies.
 * It is also used in application to populate all the values, before the other modules are created.
 * This class also has a lot of nested classes, to categorize the stored values.
 */
public final class Constant {

    private static final String propertyPath = "resource/config.properties";

    private static Constant instance;

    public final Price PRICE;
    public final EnemyDeathReward ENEMY_DEATH_REWARD;
    public final EnemySpeed ENEMY_SPEED;
    public final EnemyHealth ENEMY_HEALTH;

    private Constant() {
        Properties prop;
        try {
            prop = readPropertyFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PRICE = new Price(prop);
        ENEMY_DEATH_REWARD = new EnemyDeathReward(prop);
        ENEMY_SPEED = new EnemySpeed(prop);
        ENEMY_HEALTH = new EnemyHealth(prop);
    }


    public static Constant getInstance() {
        if (instance == null) {
            instance = new Constant();
        }
        return instance;
    }


    /**
     * This method populate all the values from the property file.
     *
     * @throws IOException if the file is not found
     */
    private Properties readPropertyFile() throws IOException {
        Properties prop = null;
        InputStream inputStream = null;
        try {
            prop = new Properties();
            inputStream = new FileInputStream(new File(propertyPath));
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return prop;
    }

    public static class Price {
        public final int BEARRY_POTTER;
        public final int GRIZZLY_BEAR;
        public final int SNIPER_BEAR;
        public final int SOVIET_BEAR;
        public final int BARBEARIAN;

        private Price(Properties prop) {
            BEARRY_POTTER = Integer.parseInt(prop.getProperty("bearry_potter_cost"));
            GRIZZLY_BEAR = Integer.parseInt(prop.getProperty("grizzly_bear_cost"));
            SNIPER_BEAR = Integer.parseInt(prop.getProperty("sniper_bear_cost"));
            SOVIET_BEAR = Integer.parseInt(prop.getProperty("soviet_bear_cost"));
            BARBEARIAN = Integer.parseInt(prop.getProperty("barbearian_cost"));
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
            FISHSTICK = Integer.parseInt(prop.getProperty("fishstick_value"));
            SWORDFISH = Integer.parseInt(prop.getProperty("swordfish_value"));
            FISH_AND_CHIPS = Integer.parseInt(prop.getProperty("fish_and_chips_value"));
            FISH_IN_A_BOAT = Integer.parseInt(prop.getProperty("fish_in_a_boat_value"));
            SAILFISH = Integer.parseInt(prop.getProperty("sailfish_value"));
            SHARK = Integer.parseInt(prop.getProperty("shark_value"));
            FISH_IN_A_FISH_TANK = Integer.parseInt(prop.getProperty("fish_in_a_fish_tank_value"));
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
            FISHSTICK = Double.parseDouble(prop.getProperty("fishstick_speed"));
            SWORDFISH = Double.parseDouble(prop.getProperty("swordfish_speed"));
            FISH_AND_CHIPS = Double.parseDouble(prop.getProperty("fish_and_chips_speed"));
            FISH_IN_A_BOAT = Double.parseDouble(prop.getProperty("fish_in_a_boat_speed"));
            SAILFISH = Double.parseDouble(prop.getProperty("sailfish_speed"));
            SHARK = Double.parseDouble(prop.getProperty("shark_speed"));
            FISH_IN_A_FISH_TANK = Double.parseDouble(prop.getProperty("fish_in_a_fish_tank_speed"));
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

        private EnemyHealth(Properties prop){
            FISHSTICK = Double.parseDouble(prop.getProperty("fishstick_health"));
            SWORDFISH = Double.parseDouble(prop.getProperty("swordfish_health"));
            FISH_AND_CHIPS = Double.parseDouble(prop.getProperty("fish_and_chips_health"));
            FISH_IN_A_BOAT = Double.parseDouble(prop.getProperty("fish_in_a_boat_health"));
            SAILFISH = Double.parseDouble(prop.getProperty("sailfish_health"));
            SHARK = Double.parseDouble(prop.getProperty("shark_health"));
            FISH_IN_A_FISH_TANK = Double.parseDouble(prop.getProperty("fish_in_a_fish_tank_health"));
        }
    }

}
