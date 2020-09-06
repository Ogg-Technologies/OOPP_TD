package view;

import java.awt.event.MouseListener;

public interface View extends Drawable {
    void start();

    void draw();

    void addMouseListener(MouseListener mouseListener);
}
