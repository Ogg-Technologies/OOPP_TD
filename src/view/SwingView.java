package view;

import model.ModelData;

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
}
