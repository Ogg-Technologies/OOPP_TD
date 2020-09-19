package view;

import application.ShutDownAble;
import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import model.particles.ParticleType;
import utils.Vector;
import utils.VectorF;
import view.layers.*;
import view.particles.ParticleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    public final static int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width

    private final ModelData modelData;

    private int width = 1000;
    private int height = 800;
    public final static int heightOffset = 40;//same goes for y
    private final JFrame window;

    private final Vector offset = new Vector(8, 31);

    private final WindowState windowState = new WindowState();
    private final ShutDownAble shutDownAble;

    private boolean windowHasBeenActive = false;

    private final ParticleHandler particleHandler;
    private final JPanel[] layers;

    @Override
    public Vector getOffset() {
        return offset;
    }

    public SwingView(ModelData modelData, ShutDownAble shutDownAble) {

        this.shutDownAble = shutDownAble;
        this.modelData = modelData;

        //Setup for window and every layer
        window = new JFrame();

        Background mapPanel = new Background(modelData, windowState);
        JPanel towerLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                TowerDrawer towerDrawer = new TowerDrawer(g, windowState);
                for (Tower t : modelData.getTowers()) {
                    t.accept(towerDrawer);
                }
            }
        };
        JPanel enemyLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                EnemyDrawer enemyDrawer = new EnemyDrawer(g, windowState);
                for (Enemy e : modelData.getEnemies()) {
                    e.accept(enemyDrawer);
                }
            }
        };
        ProjectileDrawer projectileLayer = new ProjectileDrawer(modelData, windowState);
        GUIPanel GUIPanel = new GUIPanel(new VectorF(0.99f, 0.01f), modelData);

        //All layers where first element is furthest back
        layers = new JPanel[]{mapPanel, towerLayer, enemyLayer, projectileLayer, GUIPanel};


        particleHandler = new ParticleHandler();
        modelData.addOnModelUpdateObserver(particleHandler);
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setOpaqueness();
        JLayeredPane layeredPane = createLayeredPane();

        this.window.add(layeredPane);
        draw();
    }

    private JLayeredPane createLayeredPane() {
        JLayeredPane returnPane = new JLayeredPane();
        for (int i = 0; i < layers.length; i++) {
            returnPane.add(layers[i], i, 0);
        }
        return returnPane;
    }

    private void setOpaqueness() {
        for (int i = 1; i < layers.length; i++) {
            layers[i].setOpaque(false);
        }
    }

    @Override
    public void draw() {

        Vector totalSize = new Vector(window.getWidth() - widthOffset, window.getHeight() - heightOffset);
        Vector tileSize = modelData.getMapSize();

        windowState.update(totalSize, tileSize);

        setSizeOfLayers(window.getSize());

        particleHandler.draw();
        window.repaint();

        //Checks if the window has closed
        if (!window.isEnabled() && windowHasBeenActive) {
            shutDownAble.shutDown();
        }
        if (window.isEnabled()) {
            windowHasBeenActive = true;
        }
    }

    private void setSizeOfLayers(Dimension size) {
        for (JPanel layer : layers) {
            layer.setSize(size);
        }
    }


    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }

    @Override
    public void createEmitter(ParticleType type, VectorF position) {
        particleHandler.createEmitter(type, position);
    }
}
