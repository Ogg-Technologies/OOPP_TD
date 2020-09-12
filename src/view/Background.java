package view;

import model.game.map.Tile;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private Tile[][] tileGrid;
    private final WindowState windowState;

    public Background(Tile[][] tileGrid, WindowState windowState) {
        this.tileGrid = tileGrid;
        this.windowState = windowState;
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
                g.fillRect(tileX * windowState.getTileSize() + windowState.getOffset().getX(),
                        tileY * windowState.getTileSize() + windowState.getOffset().getY(),
                        windowState.getTileSize(), windowState.getTileSize());
            }
        }
    }

}
