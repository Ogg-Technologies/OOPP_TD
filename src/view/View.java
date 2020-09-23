package view;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public interface View extends Drawable, MouseViewObserver, ShutDownAble, WindowPositionHelper {
    void start();

    void addMouseListener(MouseListener mouseListener);

    void addMouseMotionListener(MouseMotionListener mouseMotionListener);
}
