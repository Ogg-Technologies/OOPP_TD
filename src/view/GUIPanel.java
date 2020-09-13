package view;

import utils.Vector;
import utils.VectorF;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class GUIPanel extends JPanel {
    private VectorF pos;
    private float hp;
    private final float hpWidth = 0.01f;
    private int hpMaxHeight;

    public GUIPanel(VectorF pos, float hp) {
        this.pos = pos;
        this.hp = hp;
        this.hpMaxHeight = getHeight()-SwingView.heightOffset;
    }

    void updateHp(float hp) {
        this.hp = hp;
        this.hpMaxHeight = getHeight()-SwingView.heightOffset;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        float x = (pos.getX()*(getWidth()-SwingView.widthOffset)-(hpWidth*getWidth()));
        float y = (pos.getY()*getHeight());
        float height = ((hpMaxHeight-y)*(1 - pos.getY()));
        g.fillRect((int)x,(int)y,(int)(hpWidth*getWidth()),(int)(height)-1);//Rounding error causes -1
        g.setColor(Color.green);
        g.fillRect((int)x,(int)(y + ((1 - hp)*height)), (int)(hpWidth*getWidth()), (int)((height*hp)));
    }
}
