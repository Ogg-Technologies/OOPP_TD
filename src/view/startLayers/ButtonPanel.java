package view.startLayers;

import application.Constant;
import model.game.map.Tile;
import model.game.map.TileMap;
import utils.Vector;
import view.ColorHandler;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian, Samuel
 * Panel for start window, where all buttons are
 * It is used by view
 */
public class ButtonPanel extends JPanel {
    private final JButton startButton;
    private final TileMap[] tileMaps;
    private final JButton[] mapButtons;

    /**
     * The constructor creates all the buttons
     * @param windowState used to change view state
     * @param tileMaps
     */
    public ButtonPanel(WindowState windowState, TileMap[] tileMaps) {
        this.tileMaps = tileMaps;
        startButton = new JButton();
        startButton.addActionListener((e -> windowState.setViewStateToGame()));
        startButton.setText("Start Game");
        add(startButton);

        mapButtons = new JButton[tileMaps.length];
        for(int i = 0; i < mapButtons.length; i++){
            JButton newButton = new JButton();
            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            add(newButton);
            mapButtons[i] = newButton;
            int finalI = i;
            newButton.addActionListener(e -> System.out.println(finalI));
        }
    }

    private void updateButtons(Graphics g) {
        startButton.setSize((int)(getWidth()*0.2),(int)(getHeight()*0.1));
        startButton.setFont(new Font("serif", Font.PLAIN, (int)(getWidth()*0.035)));
        startButton.setLocation((int)(getWidth()*0.4), (int)(getHeight()*0.6));

        int y = (int) (getHeight() * .1);
        int height = (int) (getHeight() * .2);
        int width = (int) (getWidth() * .2);
        int gap = (int) (10 + .1 * width);
        int totalWidth = gap * (mapButtons.length - 1) + width * mapButtons.length;
        int startX = (getWidth() - totalWidth) / 2;
        for(int i = 0; i < mapButtons.length; i++){
            int x = startX + (width + gap) * i;
            drawMap(g, x, y, width, height, tileMaps[i]);
        }
    }

    //TODO: refactor this, This is almost identical to view.gameLayers.Background
    private void drawMap(Graphics g, int x, int y, int width, int height, TileMap tileMap){
        if(tileMap == null){
            return;
        }

        g.setColor(ColorHandler.BACKGROUND);
        g.fillRect(x, y, width, height);

        int baseX = -1;
        int baseY = -1;
        Vector mapSize = tileMap.getSize();

        int tileWidth = width / mapSize.getIntX();
        int tileHeight = height / mapSize.getIntY();

        int tileSize = Math.min(tileWidth, tileHeight);

        if(tileSize * mapSize.getIntX() < width){
            x += (width - tileSize * mapSize.getIntX()) / 2;
        }

        if(tileSize * mapSize.getIntY() < height){
            y += (height - tileSize * mapSize.getIntY()) / 2;
        }

        for (int tileY = 0; tileY < mapSize.getIntY(); tileY++) {
            for (int tileX = 0; tileX < mapSize.getIntX(); tileX++) {

                switch (tileMap.getTile(tileX, tileY)) {
                    case START:
                    case PATH:
                        g.setColor(ColorHandler.PATH);
                        break;
                    case GROUND:
                    case BASE:
                        g.setColor(ColorHandler.GROUND);
                        break;
                    default:
                        throw new UnsupportedOperationException("The tile " + tileMap.getTile(tileX, tileY)
                                + " has no supported look in View");
                }

                if (tileMap.getTile(tileX, tileY) == Tile.BASE) {
                    baseX = tileX;
                    baseY = tileY;
                }

                g.fillRect(tileX * tileSize + x, tileY * tileSize + y, tileSize, tileSize);
            }
        }

        g.drawImage(ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.BASE), baseX * tileSize + x,
                baseY * tileSize + y, tileSize, tileSize, null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateButtons(g);
    }
}
