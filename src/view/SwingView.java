package view;

import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import utils.Vector;
import view.layers.*;
import view.particles.ParticleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SwingView implements View {

    public final static int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width

    private final ModelData modelData;

    private int width = 800;
    private int height = 800;
    public final static int heightOffset = 40;//same goes for y
    private final JFrame window;

    private final Vector offset = new Vector(8, 31);

    private final WindowState windowState = new WindowState();

    private boolean windowHasBeenActive = false;

    private final ParticleHandler particleHandler;
    private final JPanel[] layers;

    private final Background background;

    public SwingView(ModelData modelData) {

        this.modelData = modelData;

        //Setup for window and every layer
        window = new JFrame();

        background = new Background(modelData, windowState);
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
        GUIPanel GUIPanel = new GUIPanel(modelData);

        particleHandler = new ParticleHandler(windowState);
        modelData.addOnModelEventListener(particleHandler);

        //All layers where first element is furthest back
        layers = new JPanel[]{background, towerLayer, enemyLayer, projectileLayer, particleHandler, GUIPanel};
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

        window.repaint();

        if (window.isEnabled()) {
            windowHasBeenActive = true;
        }
    }

    private void setSizeOfLayers(Dimension size) {
        int width = size.width - widthOffset;
        int height = size.height - heightOffset;
        for (JPanel layer : layers) {
            layer.setSize(width, height);
        }
    }


    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }

    @Override
    public Vector getOffset() {
        return offset;
    }

    @Override
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
        window.addMouseMotionListener(mouseMotionListener);
    }

    private Vector prevTilePos = null;

    @Override
    public void updateMousePosition(Vector v) {
        Vector tilePos = convertFromRealPosToTilePos(v);
        if (tilePos == null) {
            background.setMousePosToNull();
            prevTilePos = null;
            return;
        }
        //Optimizing, doesn't call it for update if the tile hasn't changed
        if (prevTilePos == null || !prevTilePos.equals(tilePos)) {
            background.setMousePos(tilePos, modelData.isValidTile(tilePos));
            prevTilePos = tilePos;
        }
    }

    @Override
    public Vector convertFromRealPosToTilePos(Vector v) {
        int offsettedX = v.getIntX() - windowState.getTileMapOffset().getIntX();
        int offsettedY = v.getIntY() - windowState.getTileMapOffset().getIntY();
        if (offsettedX < 0 || offsettedY < 0) {
            return null;
        }
        int tileX = offsettedX / windowState.getTileSize();
        int tileY = offsettedY / windowState.getTileSize();
        if (tileX < modelData.getMapSize().x && tileY < modelData.getMapSize().y) {
            return new Vector(tileX, tileY);
        }
        return null;
    }

    @Override
    public boolean isShutDown() {
        return windowHasBeenActive && !window.isEnabled();
    }
}
