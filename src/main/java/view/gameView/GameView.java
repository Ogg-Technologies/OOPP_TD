package view.gameView;

import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import utils.Vector;
import view.*;
import view.gameView.layers.*;
import view.particles.ParticleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * @author Sebastian, Samuel, Erik
 * Game view component, where it displays everything that should be displayable for everything in game
 * Is created in Applicaton, and updated by applicationLoop
 */
public class GameView implements IGameView {

    public final static int WIDTH_OFFSET = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    public final static int HEIGHT_OFFSET = 40;//same goes for y

    public final static int LEFT_OFFSET = 8;
    public final static int UP_OFFSET = 31;

    private final Background background;
    private final GUIPanel guiPanel;

    private final OnClose onClose;

    private final JFrame window;
    private final ModelData modelData;
    private final WindowState windowState;
    private final JPanel[] layers;
    private final JLayeredPane layersPane;


    public GameView(JFrame window, ModelData modelData, OnClose onClose) {
        this.window = window;
        this.modelData = modelData;
        this.onClose = onClose;
        windowState = WindowState.INSTANCE;

        window.getContentPane().setBackground(ColorHandler.GAME_BACKGROUND);
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
        guiPanel = new GUIPanel(modelData, windowState);

        ParticleHandler particleHandler = new ParticleHandler(windowState);
        modelData.addOnModelEventListener(particleHandler);

        //All layers where first element is furthest back
        layers = new JPanel[]{background, towerLayer, enemyLayer, projectileLayer, particleHandler, guiPanel};

        setOpaqueness(layers);
        layersPane = createLayeredPane(layers);

        window.add(layersPane);
        SwingUtilities.updateComponentTreeUI(window);
    }


    @Override
    public void draw() {
        //Swing method for setting the window in focused mode
        //It is used for reading keyboard inputs
        if (window.isActive()) {
            window.requestFocusInWindow();
        }

        Vector totalSize = new Vector(window.getWidth() - WIDTH_OFFSET, window.getHeight() - HEIGHT_OFFSET);
        Vector tileAmount = modelData.getMapSize();

        windowState.update(totalSize, tileAmount);
        setSizeOfLayers(window.getSize(), layers);
        window.repaint();
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }

    @Override
    public Vector getOffset() {
        return new Vector(LEFT_OFFSET, UP_OFFSET);
    }

    @Override
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener) {
        window.addMouseMotionListener(mouseMotionListener);
    }

    @Override
    public void addButtonClickHandler(ButtonClickHandler buttonClickHandler) {
        guiPanel.setupButtons(buttonClickHandler, this::closeThis);
    }


    @Override
    public void addState(ControllerStateValue controllerStateValue) {
        guiPanel.addState(controllerStateValue);
    }

    @Override
    public void addKeyListener(KeyListener keyListener) {
        window.addKeyListener(keyListener);
    }

    @Override
    public int maxTowersInTowerPanel() {
        return GUIPanel.MAX_TOWERS;
    }


    private Vector prevTilePos = null;

    @Override
    public void updateMousePosition(Vector v) {
        Vector tilePos = convertFromRealPosToTilePos(v);
        if (tilePos == null) {
            guiPanel.updateMouseTilePos(null);
            background.setMousePosToNull();
            prevTilePos = null;
            return;
        }
        //Optimizing, doesn't call it for update if the tile hasn't changed
        if (prevTilePos == null || !prevTilePos.equals(tilePos)) {
            background.setMousePos(tilePos, modelData.isValidTile(tilePos));
            prevTilePos = tilePos;
            guiPanel.updateMouseTilePos(tilePos);
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

    private void closeThis() {
        window.remove(layersPane);
        onClose.close();
    }

    private JLayeredPane createLayeredPane(JPanel[] layers) {
        JLayeredPane returnPane = new JLayeredPane();
        for (int i = 0; i < layers.length; i++) {
            returnPane.add(layers[i], i, 0);
        }
        return returnPane;
    }

    private void setOpaqueness(JPanel[] layers) {
        for (JPanel layer : layers) {
            layer.setOpaque(false);
        }
    }

    private void setSizeOfLayers(Dimension size, JPanel[] layers) {
        int width = size.width - WIDTH_OFFSET;
        int height = size.height - HEIGHT_OFFSET;
        for (JPanel layer : layers) {
            layer.setSize(width, height);
        }

    }
}

