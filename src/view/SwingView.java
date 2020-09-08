package view;

import model.ModelData;

import javax.swing.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final Background mapPanel;
    private final TowerDrawer towerDrawer;

    private int width = 800;
    private int height = 1000;

    private final int xOffset = 8;
    private final int yOffset = 31;

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public SwingView(ModelData modelData) {
        window = new Window();
        this.modelData = modelData;
        this.mapPanel = new Background(this.modelData.getTileMap());
        this.towerDrawer = new TowerDrawer();
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        this.mapPanel.setSize(window.getSize());
        JLayeredPane layeredPane = new JLayeredPane();

        this.towerDrawer.setOpaque(false);

        layeredPane.add(this.mapPanel, 0, 0);
        layeredPane.add(this.towerDrawer, 1, 0);

        this.window.add(layeredPane);
        draw();
    }

    @Override
    public void draw() {

        width = window.getWidth() - xOffset;
        height = window.getHeight() - yOffset;

        int mapLength;
        int xPos = 0;
        int yPos = 0;
        if (width > height) {
            mapLength = height;
            xPos += (width - height) / 2;
        } else {
            mapLength = width;
            yPos += (height - width) / 2;
        }

        int tileWidth = mapLength / modelData.getTileMap().length;

        mapPanel.setSize(window.getSize());
        towerDrawer.setSize(window.getSize());

        mapPanel.drawBackground(xPos, yPos, tileWidth);
        towerDrawer.draw(modelData.getTowers(), xPos, yPos, tileWidth);
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
