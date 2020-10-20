package view.mainMenuView;

import model.game.map.TileMap;
import utils.Vector;
import view.ColorHandler;
import view.Drawable;
import view.OnCloseWithIndex;
import view.mainMenuView.layers.ButtonPanel;
import view.mainMenuView.layers.ImagePanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian, Samuel
 * This is the view component for displaying everything on the main menu screen.
 * It is created by Application
 */
public class MainMenuView implements Drawable {
    public final static int WIDTH_OFFSET = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    public final static int HEIGHT_OFFSET = 40;//same goes for y

    private final JPanel[] layers;
    private final JLayeredPane layersPane;
    private final JFrame window;
    private final OnCloseWithIndex onCloseWithIndex;

    public MainMenuView(JFrame window, TileMap[] tileMaps, OnCloseWithIndex onCloseWithIndex) {
        this.window = window;
        this.onCloseWithIndex = onCloseWithIndex;
        window.getContentPane().setBackground(ColorHandler.GROUND);

        ImagePanel backgroundImage = new ImagePanel(new Vector(window.getWidth() - WIDTH_OFFSET, window.getHeight() - HEIGHT_OFFSET));
        ButtonPanel buttons = new ButtonPanel(this::closeThis, tileMaps);

        layers = new JPanel[]{backgroundImage, buttons};

        setOpaqueness(layers);
        layersPane = createLayeredPane(layers);

        window.add(layersPane);
        SwingUtilities.updateComponentTreeUI(window);
    }

    @Override
    public void draw() {
        setSizeOfLayers(window.getSize(), layers);
        window.repaint();
    }

    private void closeThis(int index) {
        if (index >= 0) {
            window.remove(layersPane);
            onCloseWithIndex.close(index);
        }
    }


    private void setSizeOfLayers(Dimension size, JPanel[] layers) {
        int width = size.width - WIDTH_OFFSET;
        int height = size.height - HEIGHT_OFFSET;
        for (JPanel layer : layers) {
            layer.setSize(width, height);
        }

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
}
