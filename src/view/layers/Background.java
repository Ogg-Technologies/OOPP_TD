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
    private boolean validTile = true;

    private static final BufferedImage baseImage = getBaseImage();

    public Background(ModelData modelData, WindowState windowState) {
        this.modelData = modelData;
        this.windowState = windowState;
    }

    public void setMousePos(Vector pos, boolean validTile){
        this.pos = pos;
        this.validTile = validTile;
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
            paintOverlay(pos, g);
        }

        g.drawImage(baseImage, baseX * windowState.getTileSize() + windowState.getOffset().getX(),
                baseY * windowState.getTileSize() + windowState.getOffset().getY()
                ,windowState.getTileSize(),windowState.getTileSize(), null );
    }

    private void paintOverlay(Vector tilePos, Graphics g){

        if(tilePos.getX() >= 0 && tilePos.getY() >= 0 && tilePos.getX() < modelData.getMapSize().getX() && tilePos.getY() < modelData.getMapSize().getY()) {
            if(validTile){
                g.setColor(new Color(0, 0, 0, 50));
            }else {
                g.setColor(new Color(255, 0, 0, 50));
            }
            g.fillRect(tilePos.getX() * windowState.getTileSize() + windowState.getOffset().getX(),
                    tilePos.getY() * windowState.getTileSize() + windowState.getOffset().getY(),
                    windowState.getTileSize(), windowState.getTileSize());
        }
    }

}
