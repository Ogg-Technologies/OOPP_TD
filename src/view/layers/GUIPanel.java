package view.layers;

import model.ModelData;
import utils.VectorF;
import view.SwingView;

import javax.swing.*;
import java.awt.*;

public class GUIPanel extends JPanel {
    private VectorF pos;
    private final float hpWidth = 0.01f;
    private final ModelData modelData;

    public GUIPanel(VectorF pos, ModelData modelData) {
        this.pos = pos;
        this.modelData = modelData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double hp = modelData.getBaseHealth().getFraction();
        int hpMaxHeight = getHeight() - SwingView.heightOffset;
        g.setColor(Color.red);
        float x = (pos.getX() * (getWidth() - SwingView.widthOffset) - (hpWidth * getWidth()));
        float y = (pos.getY() * getHeight());
        float height = ((hpMaxHeight - y) * (1 - pos.getY()));
        g.fillRect((int) x, (int) y, (int) (hpWidth * getWidth()), (int) (height) - 1);//Rounding error causes -1
        g.setColor(Color.green);
        g.fillRect((int) x, (int) (y + ((1 - hp) * height)), (int) (hpWidth * getWidth()), (int) ((height * hp)));
    }
}
