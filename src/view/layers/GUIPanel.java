package view.layers;

import model.ModelData;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian, Samuel, Erik
 * Display gui elemets.
 * Is used by SwingView.
 */

public class GUIPanel extends JPanel {


    private static final Color GUI_BACKGROUND_COLOR = new Color(196, 196, 196);

    private final ModelData modelData;
    private final JLabel moneyLabel = new JLabel();
    private final JLabel healthBarLabel = new JLabel();


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
        drawMoneyDisplay(g);
    }


    public GUIPanel(ModelData modelData) {
        this.modelData = modelData;
        setLayout(null);
        add(moneyLabel);
        add(healthBarLabel);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BaseHealth data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Color HEALTH_COLOR = Color.GREEN;

    private static final double HEALTH_BAR_LEFT = .145;
    private static final double HEALTH_BAR_UP = .02;
    private static final double HEALTH_BAR_WIDTH = .01;
    private static final double HEALTH_BAR_HEIGHT = .83;
    private static final double HEALTH_LABEL_LEFT = HEALTH_BAR_LEFT - .02;
    private static final double HEALTH_LABEL_UP = HEALTH_BAR_UP + HEALTH_BAR_HEIGHT;
    private static final double HEALTH_LABEL_WIDTH = HEALTH_BAR_WIDTH + .04;
    private static final double HEALTH_LABEL_HEIGHT = .04;

    private void drawHealthBar(Graphics g) {
        int x = (int) (HEALTH_BAR_LEFT * getWidth());
        int fullY = (int) (HEALTH_BAR_UP * getHeight());
        int width = (int) (HEALTH_BAR_WIDTH * getWidth());
        int fullHeight = (int) (HEALTH_BAR_HEIGHT * getHeight());
        int fractionY = (int) (fullY + fullHeight * (1 - modelData.getBaseHealth().getFraction()));
        int fractionHeight = (int) (fullHeight * modelData.getBaseHealth().getFraction());

        g.setColor(GUI_BACKGROUND_COLOR);
        g.fillRect(x, fullY, width, fullHeight);

        g.setColor(HEALTH_COLOR);
        g.fillRect(x, fractionY, width, fractionHeight);

        int healthBarLabelWidth = (int)(HEALTH_LABEL_WIDTH * getWidth());
        healthBarLabel.setLocation((int) (HEALTH_LABEL_LEFT * getWidth()), (int) (HEALTH_LABEL_UP * getHeight()));
        healthBarLabel.setSize(healthBarLabelWidth, (int)(HEALTH_LABEL_HEIGHT * getHeight()));
        healthBarLabel.setText("" + (int)(Math.round(modelData.getBaseHealth().getFraction() * 100)) + "%");
        int fontSize = (int) (HEALTH_LABEL_HEIGHT * getHeight() / 2);
        healthBarLabel.setFont(new Font("serif", Font.PLAIN, fontSize));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //MoneyLabel data
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final double MONEY_LEFT = .01;
    private static final double MONEY_UP = .02;
    private static final double MONEY_WIDTH = .12;
    private static final double MONEY_HEIGHT = .06;

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
