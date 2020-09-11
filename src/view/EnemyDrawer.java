package view;

import model.game.enemy.ImmutableEnemy;
import model.game.enemy.concreteenemies.BasicEnemy;
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

public class EnemyDrawer extends JPanel {
    private final Map<Class<? extends ImmutableEnemy>, BufferedImage> enemyDrawerMap = new HashMap<>();

    private Vector pos = new Vector(0, 0);
    private int tileWidth = 0;
    private List<? extends ImmutableEnemy> enemies = null;

    public EnemyDrawer() {
        setup();
    }

    void draw(List<? extends ImmutableEnemy> enemies, Vector pos, int tileWidth) {
        this.pos = pos;
        this.enemies = enemies;
        this.tileWidth = tileWidth;
    }


    private void setup() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        enemyDrawerMap.put(BasicEnemy.class, image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ImmutableEnemy e : enemies) {
            BufferedImage curTower = enemyDrawerMap.get(e.getClass());

            int x = (int) (tileWidth * e.getPos().getX() + pos.getX());
            int y = (int) (tileWidth * e.getPos().getY() + pos.getY());

            g.drawImage(curTower, x, y, tileWidth, tileWidth, null);
        }

    }
}
