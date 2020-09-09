package view;

import model.game.map.Tile;
import utils.Vector;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private Tile[][] tileGrid;
    private Vector pos = new Vector(0,0);
    private int tileWidth = 0;

    public Background(Tile[][] tileGrid) {
        this.tileGrid = tileGrid;
    }

    void drawBackground(Vector pos, int tileWidth) {
        this.pos = pos;
        this.tileWidth = tileWidth;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int tileY = 0; tileY < this.tileGrid.length; tileY++) {
            for (int tileX = 0; tileX < this.tileGrid[0].length; tileX++) {
                if(this.tileGrid[tileY][tileX] == Tile.GROUND) {
                    g.setColor(Color.decode("#FFCC99"));
                } else {
                    g.setColor(Color.decode("#D3D3D3"));
                }
                g.fillRect(tileX * tileWidth + this.pos.getX(), tileY * tileWidth + this.pos.getY(), tileWidth, tileWidth);
            }
        }
    }

}
