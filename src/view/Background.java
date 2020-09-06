package view;

import model.game.map.Tile;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private int width;
    private int height;
    private Tile[][] tileGrid;
    public Background(int width, int height, Tile[][] tileGrid) {
        this.width = width;
        this.height = height;
        this.tileGrid = tileGrid;
        drawBackground();
    }

    private void drawBackground() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mapLength;
        int tileWidth;
        if (width > height) {
            mapLength = height;
        } else {
            mapLength = width;
        }
        tileWidth = mapLength/10;
        for(int tileY = 0; tileY < this.tileGrid.length; tileY++) {
            for(int tileX = 0; tileX < this.tileGrid[0].length; tileX++) {
                if(this.tileGrid[tileY][tileX] == Tile.GROUND) {
                    g.setColor(Color.decode("#FFCC99"));
                } else {
                    g.setColor(Color.decode("#D3D3D3"));
                }
                g.fillRect(tileX*tileWidth,tileY*tileWidth,tileWidth,tileWidth);
            }
        }
    }
}
