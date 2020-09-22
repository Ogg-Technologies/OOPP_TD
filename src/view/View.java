package view;

import utils.Vector;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface View extends Drawable, MouseViewObserver, ShutDownAble {
    void start();

    void draw();

    void addMouseListener(MouseListener mouseListener);

    void addMouseMotionListener(MouseMotionListener mouseMotionListener);

    Vector getOffset();

    Vector convertRealPosToTilePos(Vector v);

}
