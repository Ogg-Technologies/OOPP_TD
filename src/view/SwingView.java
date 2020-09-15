package view;

import application.ShutDownAble;
import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import utils.Vector;
import utils.VectorF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final Background mapPanel;
    private final JPanel towerLayer;
    private final JPanel enemyLayer;
    private final GUIPanel GUIPanel;

    private int width = 800;
    private int height = 1000;

    final static int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    final static int heightOffset = 40;//same goes for y

    private Integer tileWidth = 0;
    private Vector pos;

    private final Vector offset = new Vector(8, 31);

    private final WindowState windowState = new WindowState();
    private final ShutDownAble shutDownAble;

    @Override
    public Vector getOffset() {
        return offset;
    }

    private boolean windowHasBeenActive = false;

    public SwingView(ModelData modelData, ShutDownAble shutDownAble) {

        this.shutDownAble = shutDownAble;

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
        this.GUIPanel = new GUIPanel(new VectorF(0.99f, 0.01f)
                , modelData.getBaseHealth().getFraction());
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mapPanel.setSize(window.getSize());
        JLayeredPane layeredPane = new JLayeredPane();

        this.towerLayer.setOpaque(false);
        this.enemyLayer.setOpaque(false);
        this.GUIPanel.setOpaque(false);

        layeredPane.add(this.mapPanel, 0, 0);
        layeredPane.add(this.towerLayer, 1, 0);
        layeredPane.add(this.enemyLayer, 2, 0);
        layeredPane.add(this.GUIPanel, 3, 0);

        this.window.add(layeredPane);
        draw();
    }

    @Override
    public void draw() {


        Vector totalSize = new Vector(window.getWidth() - widthOffset, window.getHeight() - heightOffset);
        Vector tileSize = new Vector(modelData.getTileMap()[0].length, modelData.getTileMap().length);

        windowState.update(totalSize, tileSize);
        GUIPanel.updateHp(modelData.getBaseHealth().getFraction());

        mapPanel.setSize(window.getSize());
        towerLayer.setSize(window.getSize());
        enemyLayer.setSize(window.getSize());
        GUIPanel.setSize(window.getSize());
        window.repaint();


        if (!window.isActive() && windowHasBeenActive) {
            shutDownAble.shutDown();
        }
        if (window.isActive()) {
            windowHasBeenActive = true;
        }
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }

}
