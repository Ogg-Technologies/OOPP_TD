package view;

import model.particles.EmitterCreator;
import utils.Vector;

import java.awt.event.MouseListener;

public interface View extends Drawable, EmitterCreator {
    void start();

    void draw();

    void addMouseListener(MouseListener mouseListener);

    Vector getOffset();

}
