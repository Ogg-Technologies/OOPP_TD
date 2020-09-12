package view;

import model.game.tower.Tower;
import model.game.tower.concretetowers.BasicTower;
import utils.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TowerDrawer extends JPanel {

    private final Map<Class<? extends Tower>, BufferedImage> towerDrawerMap = new HashMap<>();

    private Vector pos = new Vector(0,0);
    private int tileWidth = 0;
    private List<? extends Tower> towers = null;

    public TowerDrawer() {
        setup();
    }

    void draw(List<? extends Tower> towers, Vector pos, int tileWidth) {
        this.pos = pos;
        this.towers = towers;
        this.tileWidth = tileWidth;
    }


    private void setup() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource/tower.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        towerDrawerMap.put(BasicTower.class, image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Tower t : towers) {
            BufferedImage curTower = towerDrawerMap.get(t.getClass());

            int x = tileWidth * t.getPos().getX() + pos.getX();
            int y = tileWidth * t.getPos().getY() + pos.getY();

            g.drawImage(curTower, x, y, tileWidth, tileWidth, null);
        }

    }
}
