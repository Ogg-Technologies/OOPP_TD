package view;

import model.ModelData;
import utils.Vector;

import javax.swing.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final Background mapPanel;
    private final TowerDrawer towerDrawer;
    private final EnemyDrawer enemyDrawer;

    private int width = 800;
    private int height = 1000;

    private final int xStart = 8; //xStart = Actual start of screen
    private final int yStart = 31;//same goes for y
    private final int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    private final int heightOffset = 40;//same goes for y

    public int getXOffset() {
        return xStart;
    }

    public int getYOffset() {
        return yStart;
    }

    public SwingView(ModelData modelData) {
        window = new Window();
        this.modelData = modelData;
        this.mapPanel = new Background(this.modelData.getTileMap());
        this.towerDrawer = new TowerDrawer();
        this.enemyDrawer = new EnemyDrawer();
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        this.mapPanel.setSize(window.getSize());
        JLayeredPane layeredPane = new JLayeredPane();

        this.towerDrawer.setOpaque(false);
        this.enemyDrawer.setOpaque(false);

        layeredPane.add(this.mapPanel, 0, 0);
        layeredPane.add(this.towerDrawer, 1, 0);
        layeredPane.add(this.enemyDrawer, 2, 0);

        this.window.add(layeredPane);
        draw();
    }

    @Override
    public void draw() {

        width = window.getWidth() - widthOffset;
        height = window.getHeight() - heightOffset;

        Vector pos = new Vector(0,0);

        int divider;
        int tileWidth;
        if(width/modelData.getTileMap()[0].length > height/modelData.getTileMap().length) {
            divider = modelData.getTileMap().length;
            tileWidth = height / divider;
        } else {
            divider = modelData.getTileMap()[0].length;
            tileWidth = width / divider;
        }

        int mapWidth = tileWidth * modelData.getTileMap()[0].length;
        int mapHeight = tileWidth * modelData.getTileMap().length;

        int startX = (width-mapWidth)/2;
        int startY = (height-mapHeight)/2;

        pos = pos.plus(new Vector(startX,startY));

        mapPanel.setSize(width,height);
        towerDrawer.setSize(window.getSize());
        enemyDrawer.setSize(window.getSize());
        mapPanel.drawBackground(pos, tileWidth);
        towerDrawer.draw(modelData.getTowers(), pos, tileWidth);
        enemyDrawer.draw(modelData.getEnemies(), pos, tileWidth);
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
