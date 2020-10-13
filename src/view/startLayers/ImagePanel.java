package view.startLayers;

import utils.Vector;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private final BufferedImage image;
    private int width;
    private int height;
    private int x;
    private int y;

    public ImagePanel(Vector size) {
        this.image = ImageHandler.getImage("resource/startScreen.png");
        this.width = size.getIntX();
        this.height = size.getIntY();
        updateImage();
    }

    private void updateImage() {
        if (getWidth() / 16 > getHeight() / 9) {
            width = getWidth();
            height = 9 * getWidth() / 16;
            y = -(height - getHeight()) / 2;
            x = 0;
        } else {
            width = getHeight() * 16 / 9;
            height = getHeight();
            x = -(width - getWidth()) / 2;
            y = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateImage();
        g.drawImage(image, x, y, width, height, null);
    }
}
