package view.layers;

import model.ModelData;
import utils.VectorD;
import view.SwingView;

import javax.swing.*;
import java.awt.*;

public class GUIPanel extends JPanel {

    private static final Color HEALTH_COLOR = Color.GREEN;
    private static final Color DAMAGE_COLOR = Color.RED;
    private static final double HEALTH_BAR_WIDTH = 0.01;

    private VectorD pos;
    private final ModelData modelData;

    public GUIPanel(VectorD fractionPos, ModelData modelData) {
        this.pos = fractionPos;
        this.modelData = modelData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {
        double hp = modelData.getBaseHealth().getFraction();
        int hpMaxHeight = getHeight() - SwingView.heightOffset;
        double x = (pos.x * (getWidth() - SwingView.widthOffset) - (HEALTH_BAR_WIDTH * getWidth()));
        double y = (pos.y * getHeight());
        double height = ((hpMaxHeight - y) * (1 - pos.y));

        g.setColor(DAMAGE_COLOR);
        g.fillRect((int) x, (int) y, (int) (HEALTH_BAR_WIDTH * getWidth()), (int) (height) - 1);//Rounding error causes -1

        g.setColor(HEALTH_COLOR);
        g.fillRect((int) x, (int) (y + ((1 - hp) * height)), (int) (HEALTH_BAR_WIDTH * getWidth()), (int) ((height * hp)));
    }
}
