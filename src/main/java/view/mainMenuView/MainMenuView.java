package view.mainMenuView;

import model.game.map.TileMap;
import utils.Vector;
import view.ColorHandler;
import view.Drawable;
import view.WindowState;
import view.mainMenuView.layers.ButtonPanel;
import view.mainMenuView.layers.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class MainMenuView implements Drawable {
    public final static int WIDTH_OFFSET = 17;//WidthOffset = Actual subtraction on width needed to get usable width
    public final static int HEIGHT_OFFSET = 40;//same goes for y

    private final JPanel[] layers;
    private final JLayeredPane layersPane;
    private final JFrame window;

    public MainMenuView(JFrame window, TileMap[] tileMaps){
        this.window = window;
        window.getContentPane().setBackground(ColorHandler.GROUND);

        ImagePanel backgroundImage = new ImagePanel(new Vector(window.getWidth() - WIDTH_OFFSET, window.getHeight() - HEIGHT_OFFSET));
        ButtonPanel buttons = new ButtonPanel(WindowState.INSTANCE, tileMaps);

        layers = new JPanel[]{backgroundImage, buttons};

        setOpaqueness(layers);
        layersPane = createLayeredPane(layers);

        window.add(layersPane);
        SwingUtilities.updateComponentTreeUI(window);
    }

    @Override
    public void draw(){
        setSizeOfLayers(window.getSize(), layers);
        window.repaint();
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
