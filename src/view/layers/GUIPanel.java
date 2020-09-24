package view.layers;

import model.ModelData;
import utils.VectorD;
import view.SwingView;

import javax.swing.*;
import java.awt.*;

public class GUIPanel extends JPanel {

    private static final Color HEALTH_COLOR = Color.GREEN;
    private static final Color DAMAGE_COLOR = Color.RED;
    private static final Color GUI_BACKGROUND_COLOR = new Color(196, 196, 196);
    private static final double HEALTH_BAR_WIDTH = 0.01;
    //Positions and size of money label, change these if you want to move money label
    private static final double MONEY_LEFT = .01;
    private VectorD pos;
    private final ModelData modelData;
    private static final double MONEY_UP = .02;
    private static final double MONEY_WIDTH = .12;

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

    private static final double MONEY_HEIGHT = .06;
    private JLabel moneyLabel = new JLabel();

    public GUIPanel(VectorD fractionPos, ModelData modelData) {
        this.pos = fractionPos;
        this.modelData = modelData;
        setLayout(null);
        add(moneyLabel);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
        drawMoneyDisplay(g);
    }

    private void drawMoneyDisplay(Graphics g) {
        int x = (int) (MONEY_LEFT * getWidth());
        int y = (int) (MONEY_UP * getHeight());
        int width = (int) (MONEY_WIDTH * getWidth());
        int height = (int) (MONEY_HEIGHT * getHeight());
        int fontSize = (int) (width / 4.2); //.2 because some sort of margin
        g.setColor(GUI_BACKGROUND_COLOR);
        g.fillRect(x, y, width, height);
        moneyLabel.setLocation(x, y);
        moneyLabel.setSize(width, height);
        moneyLabel.setText("$ " + modelData.getMoney());
        moneyLabel.setFont(new Font("serif", Font.BOLD, fontSize));
    }
}
