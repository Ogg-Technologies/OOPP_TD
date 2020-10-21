package view.gameView.layers.GUIObjects;

import config.Config;
import model.game.enemy.Enemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;
import view.ColorHandler;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Oskar, Sebastian
 * <p>
 * Draws the enemy info panel to the right containing information about the latest enemyType
 */
public class EnemyInfoDrawer {
    private static final double LEFT = .87;
    private static final double TOP = .1;
    private static final double WIDTH = .12;
    private static final double HEIGHT = WindowState.MAP_HEIGHT - TOP - .01;

    private static final String NEW_LINE = "<br>";

    private final JLabel infoText;
    private final Map<Class<? extends Enemy>, String> enemyInfoStringMap;
    private final Set<Class<? extends Enemy>> seenEnemies;
    private Class<? extends Enemy> lastSeenEnemy;

    private static final Map<Class<? extends Enemy>, String> enemyImagePathMap = setupImagePathMap();

    private static Map<Class<? extends Enemy>, String> setupImagePathMap() {
        HashMap<Class<? extends Enemy>, String> pathMap = new HashMap<>();
        pathMap.put(BasicEnemy.Fishstick.class, Config.ImagePath.FISHSTICK);
        pathMap.put(BasicEnemy.Swordfish.class, Config.ImagePath.SWORDFISH);
        pathMap.put(BasicEnemy.FishAndChips.class, Config.ImagePath.FISH_AND_CHIPS);
        pathMap.put(BasicEnemy.FishInABoat.class, Config.ImagePath.FISH_IN_A_BOAT);
        pathMap.put(BasicEnemy.Sailfish.class, Config.ImagePath.SAILFISH);
        pathMap.put(BasicEnemy.Shark.class, Config.ImagePath.SHARK);
        pathMap.put(BasicEnemy.FishInAFishTank.class, Config.ImagePath.FISH_IN_A_FISH_TANK);
        pathMap.put(BasicEnemy.TankSinatra.class, Config.ImagePath.TANK_SINATRA);
        pathMap.put(FlyingFish.class, Config.ImagePath.FLYING_FISH);
        return pathMap;
    }

    public EnemyInfoDrawer(JLabel infoText) {
        this.infoText = infoText;
        this.enemyInfoStringMap = setupInfoStringMap();
        seenEnemies = new HashSet<>();
    }

    private Map<Class<? extends Enemy>, String> setupInfoStringMap() {
        Map<Class<? extends Enemy>, String> map = new HashMap<>();

        map.put(BasicEnemy.Fishstick.class, "Fishstick" + NEW_LINE + NEW_LINE
                + createHealthString(Config.Fishstick.HEALTH) + NEW_LINE
                + createSpeedString(Config.Fishstick.SPEED) + NEW_LINE
                + createMoneyString(Config.Fishstick.VALUE) + NEW_LINE + NEW_LINE
                + Config.Fishstick.INFO_TEXT);

        map.put(BasicEnemy.Swordfish.class, "Swordfish" + NEW_LINE + NEW_LINE
                + createHealthString(Config.Swordfish.HEALTH) + NEW_LINE
                + createSpeedString(Config.Swordfish.SPEED) + NEW_LINE
                + createMoneyString(Config.Swordfish.VALUE) + NEW_LINE + NEW_LINE
                + Config.Swordfish.INFO_TEXT);

        map.put(BasicEnemy.FishAndChips.class, "Fish and Chips" + NEW_LINE + NEW_LINE
                + createHealthString(Config.FishAndChips.HEALTH) + NEW_LINE
                + createSpeedString(Config.FishAndChips.SPEED) + NEW_LINE
                + createMoneyString(Config.FishAndChips.VALUE) + NEW_LINE + NEW_LINE
                + Config.FishAndChips.INFO_TEXT);

        map.put(BasicEnemy.FishInABoat.class, "Fish in a Boat" + NEW_LINE + NEW_LINE
                + createHealthString(Config.FishInABoat.HEALTH) + NEW_LINE
                + createSpeedString(Config.FishInABoat.SPEED) + NEW_LINE
                + createMoneyString(Config.FishInABoat.VALUE) + NEW_LINE + NEW_LINE
                + Config.FishInABoat.INFO_TEXT);

        map.put(BasicEnemy.Sailfish.class, "Sailfish" + NEW_LINE + NEW_LINE
                + createHealthString(Config.Sailfish.HEALTH) + NEW_LINE
                + createSpeedString(Config.Sailfish.SPEED) + NEW_LINE
                + createMoneyString(Config.Sailfish.VALUE) + NEW_LINE + NEW_LINE
                + Config.Sailfish.INFO_TEXT);

        map.put(BasicEnemy.Shark.class, "Shark" + NEW_LINE + NEW_LINE
                + createHealthString(Config.Shark.HEALTH) + NEW_LINE
                + createSpeedString(Config.Shark.SPEED) + NEW_LINE
                + createMoneyString(Config.Shark.VALUE) + NEW_LINE + NEW_LINE
                + Config.Shark.INFO_TEXT);

        map.put(BasicEnemy.FishInAFishTank.class, "Fish in a Fish Tank" + NEW_LINE + NEW_LINE
                + createHealthString(Config.FishInAFishTank.HEALTH) + NEW_LINE
                + createSpeedString(Config.FishInAFishTank.SPEED) + NEW_LINE
                + createMoneyString(Config.FishInAFishTank.VALUE) + NEW_LINE + NEW_LINE
                + Config.FishInAFishTank.INFO_TEXT);

        map.put(BasicEnemy.TankSinatra.class, "Tank Sinatra" + NEW_LINE + NEW_LINE
                + createHealthString(Config.TankSinatra.HEALTH) + NEW_LINE
                + createSpeedString(Config.TankSinatra.SPEED) + NEW_LINE
                + createMoneyString(Config.TankSinatra.VALUE) + NEW_LINE + NEW_LINE
                + Config.TankSinatra.INFO_TEXT);

        map.put(FlyingFish.class, "Flying Fish" + NEW_LINE + NEW_LINE
                + createHealthString(Config.FlyingFish.HEALTH) + NEW_LINE
                + createSpeedString(Config.FlyingFish.SPEED) + NEW_LINE
                + createMoneyString(Config.FlyingFish.VALUE) + NEW_LINE + NEW_LINE
                + Config.FlyingFish.INFO_TEXT);

        return map;
    }

    private String createHealthString(int health) {
        return "Health: " + health + " hp";
    }

    private String createSpeedString(double speed) {
        return "Speed: " + String.format("%.1f", speed * 60) + " tiles/second";
    }

    private String createMoneyString(int money) {
        return "Kill reward: $" + money;
    }

    public void draw(Graphics g, int panelWidth, int panelHeight, Set<Class<? extends Enemy>> enemyTypesInNextWave) {
        int x = (int) (LEFT * panelWidth);
        int y = (int) (TOP * panelHeight);
        int width = (int) (WIDTH * panelWidth);
        int height = (int) (HEIGHT * panelHeight);
        int margin = (int) (0.01 * panelWidth);

        g.setColor(ColorHandler.INFO_PANEL_BACKGROUND);
        g.fillRect(x, y, width, height);

        updateLastEnemyType(enemyTypesInNextWave);
        if (lastSeenEnemy == null) {
            return;
        }

        drawEnemyImage(g, enemyImagePathMap.get(lastSeenEnemy), (int) (x + width * 0.05), (int) (y + width * 0.05), (int) (width * 0.9));
        drawEnemyInfoText(x + margin, y + width, width - 2 * margin, height - 2 * margin, lastSeenEnemy);
    }

    private void updateLastEnemyType(Set<Class<? extends Enemy>> enemyTypesInNextWave) {
        for (Class<? extends Enemy> type : enemyTypesInNextWave) {
            if (!seenEnemies.contains(type)) {
                lastSeenEnemy = type;
                seenEnemies.add(type);
            }
        }
    }


    private void drawEnemyImage(Graphics g, String imagePath, int x, int y, int size) {
        g.drawImage(ImageHandler.getImage(imagePath), x, y, size, size, null);
    }

    private void drawEnemyInfoText(int x, int y, int width, int height, Class<? extends Enemy> enemyType) {
        String text = enemyInfoStringMap.getOrDefault(enemyType, "No info text added yet");
        infoText.setLocation(x, y);
        infoText.setFont(new Font("serif", Font.BOLD, (int) (width * 0.1)));
        infoText.setSize(width, height);
        infoText.setText("<html>" + text + "</html>");
    }
}
