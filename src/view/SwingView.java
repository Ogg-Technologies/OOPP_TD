package view;

import model.ModelData;
import model.game.enemy.Enemy;
import model.game.tower.Tower;
import utils.Vector;
import view.gameLayers.*;
import view.particles.ParticleHandler;
import view.startLayers.ButtonPanel;
import view.startLayers.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Sebastian, Samuel, Erik
 * Main view component, where it displays everything that should be displayable.
 * Is created in Applicaton, and updated by applicationLoop
 */
public class SwingView implements View {

    public final static int widthOffset = 17;//WidthOffset = Actual subtraction on width needed to get usable width

    private final ModelData modelData;

    private int width = 1920;
    private int height = 1080;
    public final static int heightOffset = 40;//same goes for y
    private JFrame window;

    private final Vector offset = new Vector(8, 31);

    private final WindowState windowState = new WindowState();

    private boolean windowHasBeenActive = false;

    private ParticleHandler particleHandler;
    private JPanel[] gameLayers;
    private JPanel[] startLayers;

    private JLayeredPane gameLayersPane;
    private JLayeredPane startLayersPane;

    private Background background;
    private GUIPanel GUIPanel;
    private ControllerStateValue controllerStateValue;
    private ButtonClickHandler buttonClickHandler;

    private ViewState previousState = ViewState.START;

    public SwingView(ModelData modelData) {

        this.modelData = modelData;

        window = new JFrame();

        setupStartWindow();
    }


    private void setupGameWindow() {
        window.remove(startLayersPane);

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
        this.GUIPanel = new GUIPanel(modelData, windowState, controllerStateValue);
        this.GUIPanel.setupButtons(buttonClickHandler, windowState);

        particleHandler = new ParticleHandler(windowState);
        modelData.addOnModelEventListener(particleHandler);

        //All layers where first element is furthest back
        gameLayers = new JPanel[]{background, towerLayer, enemyLayer, projectileLayer, particleHandler, GUIPanel};

        setOpaqueness(gameLayers);
        gameLayersPane = createLayeredPane(gameLayers);

        window.add(gameLayersPane);
        SwingUtilities.updateComponentTreeUI(window);

        windowState.setViewStateToGame();
    }

    private void setupStartWindow() {
        if (gameLayersPane != null) {
            window.remove(gameLayersPane);
        }
        window.getContentPane().setBackground(ColorHandler.GROUND);

        ImagePanel backgroundImage = new ImagePanel(getWindowSize());
        ButtonPanel buttons = new ButtonPanel(windowState);

        startLayers = new JPanel[]{backgroundImage,buttons};

        setOpaqueness(startLayers);
        startLayersPane = createLayeredPane(startLayers);

        window.add(startLayersPane);
        SwingUtilities.updateComponentTreeUI(window);
        windowState.setViewStateToStart();
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        draw();
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

    @Override
    public void draw() {
        ViewState currentState = windowState.getViewState();
        if (previousState != currentState) {
            previousState = currentState;
            if (currentState == ViewState.START) {
                setupStartWindow();
            } else {
                setupGameWindow();
            }
        }
        Vector totalSize = new Vector(window.getWidth() - widthOffset, window.getHeight() - heightOffset);
        Vector tileSize = modelData.getMapSize();

        windowState.update(totalSize, tileSize);

        if (currentState == ViewState.GAME) {
            setSizeOfLayers(window.getSize(), gameLayers);
        } else {
            setSizeOfLayers(window.getSize(), startLayers);
        }

        window.repaint();

        if (window.isEnabled()) {
            windowHasBeenActive = true;
        }
    }

    private void setSizeOfLayers(Dimension size, JPanel[] layers) {
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
    public Vector getWindowSize() {
        return new Vector(window.getWidth() - widthOffset, window.getHeight() - heightOffset);
    }

    @Override
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener) {
        window.addMouseMotionListener(mouseMotionListener);
    }

    @Override
    public void addButtonClickHandler(ButtonClickHandler buttonClickHandler) {
        this.buttonClickHandler = buttonClickHandler;
    }

    @Override
    public void addState(ControllerStateValue controllerStateValue) {
        this.controllerStateValue = controllerStateValue;
    }

    private Vector prevTilePos = null;

    @Override
    public void updateMousePosition(Vector v) {
        if (windowState.getViewState() != ViewState.GAME || GUIPanel == null || background == null) {
            return;
        }
        Vector tilePos = convertFromRealPosToTilePos(v);
        if (tilePos == null) {
            GUIPanel.updateMouseTilePos(null);
            background.setMousePosToNull();
            prevTilePos = null;
            return;
        }
        //Optimizing, doesn't call it for update if the tile hasn't changed
        if (prevTilePos == null || !prevTilePos.equals(tilePos)) {
            background.setMousePos(tilePos, modelData.isValidTile(tilePos));
            prevTilePos = tilePos;
            GUIPanel.updateMouseTilePos(tilePos);
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
