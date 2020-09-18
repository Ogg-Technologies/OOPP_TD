package view;

import model.ModelData;
import model.game.map.Tile;
import utils.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class Background extends JPanel {
    private final ModelData modelData;
    private final WindowState windowState;

    private static final BufferedImage baseImage = getBaseImage();

    public Background(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        this.windowState = windowState;
    }


    private static BufferedImage getBaseImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resource/base.png"));
        } catch (IOException e) {
            throw new IOError(e);
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int baseX = -1;
        int baseY = -1;
        Vector mapSize = modelData.getMapSize();
        for (int tileY = 0; tileY < mapSize.getY(); tileY++) {
            for (int tileX = 0; tileX < mapSize.getX(); tileX++) {

                switch (modelData.getTile(tileX, tileY)) {
                    case START:
                    case PATH:
                        g.setColor(Color.decode("#D3D3D3"));
                        break;
                    case GROUND:
                    case BASE:
                    default:
                        g.setColor(Color.decode("#FFCC99"));
                        break;
                }

                if(modelData.getTile(tileX, tileY) == Tile.BASE) {
                    baseX = tileX;
                    baseY = tileY;
                }

                g.fillRect(tileX * windowState.getTileSize() + windowState.getOffset().getX(),
                        tileY * windowState.getTileSize() + windowState.getOffset().getY(),
                        windowState.getTileSize(), windowState.getTileSize());
            }
        }
        g.drawImage(baseImage, baseX * windowState.getTileSize() + windowState.getOffset().getX(),
                baseY * windowState.getTileSize() + windowState.getOffset().getY()
                ,windowState.getTileSize(),windowState.getTileSize(), null );
    }

}
