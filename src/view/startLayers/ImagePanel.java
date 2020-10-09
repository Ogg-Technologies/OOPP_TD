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

    public ImagePanel(Vector size) {
        this.image =  ImageHandler.getImage("resource/startScreen.png");
        this.width = size.getIntX();
        this.height = size.getIntY();
    }

    private void updateImage() {
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateImage();
        g.drawImage(image, 0, 0, width, height, null);
    }
}
