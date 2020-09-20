package view.layers;

import model.ModelData;
import model.game.map.Tile;
import utils.Vector;
import view.WindowState;

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
    private Vector pos = null;

    private static final BufferedImage baseImage = getBaseImage();

    public Background(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        this.windowState = windowState;
    }

    public void setMousePos(Vector pos){
        this.pos = pos;
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

        if(pos != null){
            int startX = pos.getX() - windowState.getOffset().getX();
            int startY = pos.getY() - windowState.getOffset().getY();
            if(startX >= 0 && startY >= 0){
                paintGrayOverlay(startX, startY, g);
            }
        }

        g.drawImage(baseImage, baseX * windowState.getTileSize() + windowState.getOffset().getX(),
                baseY * windowState.getTileSize() + windowState.getOffset().getY()
                ,windowState.getTileSize(),windowState.getTileSize(), null );
    }

    private void paintGrayOverlay(int startX, int startY, Graphics g){
        int tileX = startX / windowState.getTileSize();
        int tileY = startY / windowState.getTileSize();
        if(tileX >= 0 && tileY >= 0 && tileX < modelData.getMapSize().getX() && tileY < modelData.getMapSize().getY()) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillRect(tileX * windowState.getTileSize() + windowState.getOffset().getX(),
                    tileY * windowState.getTileSize() + windowState.getOffset().getY(),
                    windowState.getTileSize(), windowState.getTileSize());
        }
    }

}
