package view.gameView.layers.GUIObjects;

/**
 * @author Oskar, Sebastian
 * <p>
 * Draws the enemy info panel to the right containing information about the latest enemyType
 */
public class EnemyInfoDrawer {
//
//
//    private static final double LEFT = .86;
//    private static final double TOP = .1;
//    private static final double WIDTH = .12;
//    private static final double HEIGHT = WindowState.MAP_HEIGHT - TOP - .01;
//
//    private static final String NEW_LINE = "<br>";
//    public static final String BUFF_PREFIX = "Buff: ";
//
//    private final JLabel infoText;
//    private final Map<Class<? extends Enemy>, String> enemyImagePathMap;
//    private final Map<Class<? extends Enemy>, String> enemyInfoStringMap;
//
//    private ControllerStateValues controllerStateValues;
//    private Class<? extends Enemy> lastSelectedEnemy;
//
//
//    public EnemyInfoDrawer(JLabel infoText, Map<Class<? extends Enemy>, String> enemyInfoStringMap) {
//        this.infoText = infoText;
//        this.enemyImagePathMap = enemyInfoStringMap;
//        this.enemyInfoStringMap = setupInfoStringMap();
//    }
//
//    private Map<Class<? extends Enemy>, String> setupInfoStringMap() {
//        Map<Class<? extends Enemy>, String> map = new HashMap<>();
//
//        map.put(GrizzlyBear.class, "Grizzly Bear" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.GrizzlyBear.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.GrizzlyBear.RANGE) + NEW_LINE
//                + createDamageString(Config.GrizzlyBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.GrizzlyBear.INFO_TEXT);
//
//        map.put(BearryPotter.class, "Bearry Potter" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.BearryPotter.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.BearryPotter.RANGE) + NEW_LINE
//                + createDamageString(Config.BearryPotter.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.BearryPotter.INFO_TEXT);
//
//        map.put(SniperBear.class, "Sniper Bear" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.SniperBear.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.SniperBear.RANGE) + NEW_LINE
//                + createDamageString(Config.SniperBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.SniperBear.INFO_TEXT);
//
//        map.put(SovietBear.class, "Soviet Bear" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.SovietBear.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.SovietBear.RANGE) + NEW_LINE
//                + createDamageString(Config.SovietBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.SovietBear.INFO_TEXT);
//
//        map.put(Barbearian.class, "Barbearian" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.Barbearian.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.Barbearian.RANGE) + NEW_LINE
//                + createDamageString(Config.Barbearian.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.Barbearian.INFO_TEXT);
//
//        map.put(BearGrylls.class, "Bear Grylls" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.BearGrylls.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.BearGrylls.RANGE) + NEW_LINE
//                + createRangeBuffString(Config.BearGrylls.BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
//                + Config.BearGrylls.INFO_TEXT);
//
//        map.put(Beer.class, "Beer" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.Beer.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.Beer.RANGE) + NEW_LINE
//                + createDamageBuffString(Config.Beer.DAMAGE_BUFF_PERCENTAGE) + NEW_LINE
//                + createFireRateBuffString(Config.Beer.FIRE_RATE_BUFF_PERCENTAGE) + NEW_LINE
//                + createRangeBuffString(Config.Beer.RANGE_BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
//                + Config.Beer.INFO_TEXT);
//
//        map.put(RubixCubeBear.class, "Rubix Cube Bear" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.RubixCubeBear.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.RubixCubeBear.RANGE) + NEW_LINE
//                + createFireRateBuffString(Config.RubixCubeBear.BUFF_PERCENTAGE) + NEW_LINE + NEW_LINE
//                + Config.RubixCubeBear.INFO_TEXT);
//
//        map.put(BazookaBear.class, "Bazooka Bear" + NEW_LINE + NEW_LINE
//                + createFireRateString(Config.BazookaBear.ATTACK_DELAY) + NEW_LINE
//                + createRangeString(Config.BazookaBear.RANGE) + NEW_LINE
//                + createDamageString(Config.BazookaBear.BASE_DAMAGE) + NEW_LINE + NEW_LINE
//                + Config.BazookaBear.INFO_TEXT);
//
//        return map;
//    }
//
//    private String createHealthString(int health) {
//        return "Health: " + health + " hp";
//    }
//
//    private String createSpeedString(double speed) {
//        return "Speed: " + String.format("%.1f", speed*60) + " tiles/second";
//    }
//
//    private String createMoneyString(int money) {
//        return "Kill reward: $" + money;
//    }
//
//    public void draw(Graphics g, int panelWidth, int panelHeight) {
//        int x = (int) (LEFT * panelWidth);
//        int y = (int) (TOP * panelHeight);
//        int width = (int) (WIDTH * panelWidth);
//        int height = (int) (HEIGHT * panelHeight);
//        int margin = (int) (0.01 * panelWidth);
//
//        g.setColor(ColorHandler.INFO_PANEL_BACKGROUND);
//        g.fillRect(x, y, width, height);
//
//
//        Class<? extends Enemy> EnemyType = getLastSelectedEnemy();
//        if (EnemyType == null) {
//            return;
//        }
//
//        drawEnemyImage(g, enemyImagePathMap.get(EnemyType), (int) (x + width * 0.05), (int) (y + width * 0.05), (int) (width * 0.9));
//        drawEnemyInfoText(x + margin, y + width, width - 2 * margin, height - 2 * margin);
//    }
//
//    private Class<? extends Enemy> getLastSelectedEnemy() {
//        if (controllerStateValues == null) {
//            throw new IllegalStateException("controllerStateValues has not been set");
//        }
//        Class<? extends Enemy> EnemyType = controllerStateValues.getSelectedEnemyType();
//
//        if (EnemyType != null) {
//            lastSelectedEnemy = EnemyType;
//        }
//        return lastSelectedEnemy;
//    }
//
//    private void drawEnemyImage(Graphics g, String imagePath, int x, int y, int size) {
//        g.drawImage(ImageHandler.getImage(imagePath, Math.PI / 2), x, y, size, size, null);
//    }
//
//    private void drawEnemyInfoText(int x, int y, int width, int height) {
//        String text = enemyInfoStringMap.getOrDefault(lastSelectedEnemy, "No info text added yet");
//        infoText.setLocation(x, y);
//        infoText.setFont(new Font("serif", Font.BOLD, (int) (width * 0.1)));
//        infoText.setSize(width, height);
//        infoText.setText("<html>" + text + "</html>");
//    }
//
//    /**
//     * A setter for controllerStateValue
//     *
//     * @param controllerStateValues an interface where information about Enemys can be fetched
//     */
//    public void setControllerStateValues(ControllerStateValues controllerStateValues) {
//        this.controllerStateValues = controllerStateValues;
//    }
}
