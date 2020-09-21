package view.layers;

import model.ModelData;
import utils.VectorD;
import view.SwingView;

import javax.swing.*;
import java.awt.*;

public class GUIPanel extends JPanel {
    private final double hpWidth = 0.01;
    private VectorD pos;
    private final ModelData modelData;

    public GUIPanel(VectorD pos, ModelData modelData) {
        this.pos = pos;
        this.modelData = modelData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double hp = modelData.getBaseHealth().getFraction();
        int hpMaxHeight = getHeight() - SwingView.heightOffset;
        g.setColor(Color.red);
        double x = (pos.getX() * (getWidth() - SwingView.widthOffset) - (hpWidth * getWidth()));
        double y = (pos.getY() * getHeight());
        double height = ((hpMaxHeight - y) * (1 - pos.getY()));
        g.fillRect((int) x, (int) y, (int) (hpWidth * getWidth()), (int) (height) - 1);//Rounding error causes -1
        g.setColor(Color.green);
        g.fillRect((int) x, (int) (y + ((1 - hp) * height)), (int) (hpWidth * getWidth()), (int) ((height * hp)));
    }
}
