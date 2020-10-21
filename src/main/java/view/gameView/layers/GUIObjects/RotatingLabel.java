package view.gameView.layers.GUIObjects;

import javax.swing.*;
import java.awt.*;

public class RotatingLabel extends JLabel {
    private final double rotation;
    public RotatingLabel(double rotation){
        super();
        this.rotation = rotation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D)g;
        gx.rotate(rotation, getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }
}
