package application;

import java.io.*;
import java.util.Properties;

/**
 * @author Sebastian
 * This class stores all the values from a config file, and also fetch all the data from the same config file.
 * This is used in a lot of classes that need constants, for example the economy class with the cost of towers,
 * and values of enemies.
 * It is also used in application to populate all the values, before the other modules are created.
 * This class also has a lot of nested classes, to categorize the stored values.
 */
public class PropertyValues {

    private static final String propertyPath = "resource/config.properties";
    private static InputStream inputStream;

    /**
     * This method populate all the values from the property file.
     * @throws IOException if the file is not found
     */
    public static void populateValues() throws IOException {
        try {
            Properties prop = new Properties();
            inputStream = new FileInputStream(new File(propertyPath));

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertyPath + "' not found");
            }


            setupTowerCosts(prop);
            setupEnemyValues(prop);
            setupEnemyStats(prop);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    private static void setupTowerCosts(Properties prop){
        TowerPrices.BEARRY_POTTER_COST = Integer.parseInt(prop.getProperty("bearry_potter_cost"));
        TowerPrices.GRIZZLY_BEAR_COST = Integer.parseInt(prop.getProperty("grizzly_bear_cost"));
        TowerPrices.SNIPER_BEAR_COST = Integer.parseInt(prop.getProperty("sniper_bear_cost"));
    }

    public static class TowerPrices{
        public static int BEARRY_POTTER_COST;
        public static int GRIZZLY_BEAR_COST;
        public static int SNIPER_BEAR_COST;
    }

    private static void setupEnemyValues(Properties prop){
        EnemyValues.FISHSTICK_VALUE = Integer.parseInt(prop.getProperty("fishstick_value"));
        EnemyValues.SWORDFISH_VALUE = Integer.parseInt(prop.getProperty("swordfish_value"));
        EnemyValues.FISH_AND_CHIPS_VALUE = Integer.parseInt(prop.getProperty("fish_and_chips_value"));
        EnemyValues.FISH_IN_A_BOAT_VALUE = Integer.parseInt(prop.getProperty("fish_in_a_boat_value"));
        EnemyValues.SAILFISH_VALUE = Integer.parseInt(prop.getProperty("sailfish_value"));
        EnemyValues.SHARK_VALUE = Integer.parseInt(prop.getProperty("shark_value"));
        EnemyValues.FISH_IN_A_FISH_TANK_VALUE = Integer.parseInt(prop.getProperty("fish_in_a_fish_tank_value"));
    }

    public static class EnemyValues{
        public static int FISHSTICK_VALUE;
        public static int SWORDFISH_VALUE;
        public static int FISH_AND_CHIPS_VALUE;
        public static int FISH_IN_A_BOAT_VALUE;
        public static int SAILFISH_VALUE;
        public static int SHARK_VALUE;
        public static int FISH_IN_A_FISH_TANK_VALUE;
    }

    private static void setupEnemyStats(Properties prop){
        EnemyStats.FISHSTICK_SPEED = Double.parseDouble(prop.getProperty("fishstick_speed"));
        EnemyStats.SWORDFISH_SPEED = Double.parseDouble(prop.getProperty("swordfish_speed"));
        EnemyStats.FISH_AND_CHIPS_SPEED = Double.parseDouble(prop.getProperty("fish_and_chips_speed"));
        EnemyStats.FISH_IN_A_BOAT_SPEED = Double.parseDouble(prop.getProperty("fish_in_a_boat_speed"));
        EnemyStats.SAILFISH_SPEED = Double.parseDouble(prop.getProperty("sailfish_speed"));
        EnemyStats.SHARK_SPEED = Double.parseDouble(prop.getProperty("shark_speed"));
        EnemyStats.FISH_IN_A_FISH_TANK_SPEED = Double.parseDouble(prop.getProperty("fish_in_a_fish_tank_speed"));

        EnemyStats.FISHSTICK_HEALTH = Double.parseDouble(prop.getProperty("fishstick_health"));
        EnemyStats.SWORDFISH_HEALTH = Double.parseDouble(prop.getProperty("swordfish_health"));
        EnemyStats.FISH_AND_CHIPS_HEALTH = Double.parseDouble(prop.getProperty("fish_and_chips_health"));
        EnemyStats.FISH_IN_A_BOAT_HEALTH = Double.parseDouble(prop.getProperty("fish_in_a_boat_health"));
        EnemyStats.SAILFISH_HEALTH = Double.parseDouble(prop.getProperty("sailfish_health"));
        EnemyStats.SHARK_HEALTH = Double.parseDouble(prop.getProperty("shark_health"));
        EnemyStats.FISH_IN_A_FISH_TANK_HEALTH = Double.parseDouble(prop.getProperty("fish_in_a_fish_tank_health"));
    }

    public static class EnemyStats{

        public static double FISHSTICK_SPEED;
        public static double SWORDFISH_SPEED;
        public static double FISH_AND_CHIPS_SPEED;
        public static double FISH_IN_A_BOAT_SPEED;
        public static double SAILFISH_SPEED;
        public static double SHARK_SPEED;
        public static double FISH_IN_A_FISH_TANK_SPEED;

        public static double FISHSTICK_HEALTH;
        public static double SWORDFISH_HEALTH;
        public static double FISH_AND_CHIPS_HEALTH;
        public static double FISH_IN_A_BOAT_HEALTH;
        public static double SAILFISH_HEALTH;
        public static double SHARK_HEALTH;
        public static double FISH_IN_A_FISH_TANK_HEALTH;
    }

}
