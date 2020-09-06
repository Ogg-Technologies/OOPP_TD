package view;

import model.ModelData;

import java.awt.event.MouseListener;

public class SwingView implements View {

    private final Window window;

    public SwingView(ModelData modelData) {
        window = new Window();
    }

    @Override
    public void start() {
        window.setSize(800, 800);
        window.setVisible(true);
    }

    @Override
    public void draw() {

    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        window.addMouseListener(mouseListener);
    }
}
