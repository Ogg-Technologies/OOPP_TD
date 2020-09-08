package view;

import model.game.map.Tile;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private int width;
    private int height;
    private Tile[][] tileGrid;
    private final int xStartPos;
    private final int yStartPos;

    public Background(int width, int height, Tile[][] tileGrid, int xStartPos, int yStartPos) {
        this.width = width-xStartPos;
        this.height = height-yStartPos;
        this.tileGrid = tileGrid;
        this.xStartPos = xStartPos;
        this.yStartPos = yStartPos;
    }

    void drawBackground(int width, int height) {
        this.width = width - xStartPos;
        this.height = height - yStartPos;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mapLength;
        int tileWidth;
        int excessWidth = -xStartPos;
        int excessHeight = 0;
        if (width > height) {
            mapLength = height;
            excessWidth += (width - height)/2;
        } else {
            mapLength = width;
            excessHeight += (height - width)/2;
        }

        tileWidth = mapLength/10;
        for(int tileY = 0; tileY < this.tileGrid.length; tileY++) {
            for(int tileX = 0; tileX < this.tileGrid[0].length; tileX++) {
                if(this.tileGrid[tileY][tileX] == Tile.GROUND) {
                    g.setColor(Color.decode("#FFCC99"));
                } else {
                    g.setColor(Color.decode("#D3D3D3"));
                }
                g.fillRect(tileX*tileWidth+excessWidth,tileY*tileWidth+excessHeight,tileWidth,tileWidth);
            }
        }
    }
}
