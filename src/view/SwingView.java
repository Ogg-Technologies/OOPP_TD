package view;

import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import utils.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final Background mapPanel;
    private final JPanel towerLayer;
    private final JPanel enemyLayer;

    private int width = 800;
    private int height = 1000;

    private final int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    private final int heightOffset = 40;//same goes for y

    private Integer tileWidth = 0;
    private Vector pos;

    private final Vector offset = new Vector(8, 31);

    private final WindowState windowState = new WindowState();

    @Override
    public Vector getOffset() {
        return offset;
    }

    public SwingView(ModelData modelData) {

        window = new Window();
        this.modelData = modelData;
        this.mapPanel = new Background(this.modelData.getTileMap(), windowState);
        this.towerLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                TowerDrawer towerDrawer = new TowerDrawer(g, windowState);
                for (Tower t : modelData.getTowers()) {
                    t.accept(towerDrawer);
                }
            }
        };

        this.enemyLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                EnemyDrawer enemyDrawer = new EnemyDrawer(g, windowState);
                for (Enemy e : modelData.getEnemies()) {
                    e.accept(enemyDrawer);
                }
            }
        };
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);


        this.mapPanel.setSize(window.getSize());
        JLayeredPane layeredPane = new JLayeredPane();

        this.towerLayer.setOpaque(false);
        this.enemyLayer.setOpaque(false);

        layeredPane.add(this.mapPanel, 0, 0);
        layeredPane.add(this.towerLayer, 1, 0);
        layeredPane.add(this.enemyLayer, 2, 0);

        this.window.add(layeredPane);
        draw();
    }

    @Override
    public void draw() {

        Vector totalSize = new Vector(window.getWidth() - widthOffset, window.getHeight() - heightOffset);
        Vector tileSize = new Vector(modelData.getTileMap()[0].length, modelData.getTileMap().length);

        windowState.update(totalSize, tileSize);

        mapPanel.setSize(window.getSize());
        towerLayer.setSize(window.getSize());
        enemyLayer.setSize(window.getSize());
        window.repaint();
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
