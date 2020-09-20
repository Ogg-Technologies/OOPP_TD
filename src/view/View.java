package view;

import controller.MouseViewObserver;
import model.particles.EmitterCreator;
import utils.Vector;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface View extends Drawable, EmitterCreator, MouseViewObserver {
    void start();

    void draw();

    void addMouseListener(MouseListener mouseListener);
    void addMouseMotionListener(MouseMotionListener mouseMotionListener);

    Vector getOffset();
    Vector convertRealPosToTilePos(Vector v);

}
