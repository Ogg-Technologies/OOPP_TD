package view;

import model.game.enemy.Enemy;
import utils.Vector;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnemyLayer extends JPanel {
    private Vector pos = new Vector(0, 0);
    private int tileWidth = 0;
    private List<? extends Enemy> enemies = null;

    public EnemyLayer() {
    }

    void draw(List<? extends Enemy> enemies, Vector pos, int tileWidth) {
        this.pos = pos;
        this.enemies = enemies;
        this.tileWidth = tileWidth;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        EnemyDrawer enemyDrawer = new EnemyDrawer(g, pos, tileWidth);

        for (Enemy e : enemies) {
            e.accept(enemyDrawer);
        }
    }
}
