package view;

import model.ModelData;

import javax.swing.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final Background mapPanel;
    private final int width = 800;
    private final int height = 1000;

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
        this.mapPanel = new Background(width,height,this.modelData.getTileMap(), xOffset, yOffset);
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        this.mapPanel.setSize(window.getSize());
        this.window.add(this.mapPanel);
        draw();
    }

    @Override
    public void draw() {
        mapPanel.drawBackground(window.getWidth(), window.getHeight());

    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
