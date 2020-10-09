package view.startLayers;

import view.WindowState;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final JButton startButton;

    public ButtonPanel(WindowState windowState) {
        startButton = new JButton();
        startButton.addActionListener((e -> windowState.setViewStateToGame()));
        startButton.setText("Start Game");
        add(startButton);
    }

    private void updateButtons() {
        startButton.setSize((int)(getWidth()*0.2),(int)(getHeight()*0.1));
        startButton.setFont(new Font("serif", Font.PLAIN, (int)(getWidth()*0.035)));
        startButton.setLocation((int)(getWidth()*0.4), (int)(getHeight()*0.6));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateButtons();
    }
}
