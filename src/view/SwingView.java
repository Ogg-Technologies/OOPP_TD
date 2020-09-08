package view;

import model.ModelData;

import javax.swing.*;
import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;
    private final ModelData modelData;
    private final JPanel mainPanel;
    private final int width = 800;
    private final int height = 800;

    public SwingView(ModelData modelData) {
        window = new Window();
        this.modelData = modelData;
        this.mainPanel = new Background(width,height,this.modelData.getTileMap());
    }

    @Override
    public void start() {
        window.setSize(width, height);
        window.setVisible(true);
        this.mainPanel.setSize(window.getSize());
        this.window.add(this.mainPanel);
        draw();
    }

    @Override
    public void draw() {
        mainPanel.repaint();
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
