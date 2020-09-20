package controller;

import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SwingMouseController implements MouseListener, MouseMotionListener, MouseController {

    private final Vector offset;

    private final MouseViewObserver viewObserver;

    public SwingMouseController(Vector offset, MouseViewObserver viewObserver) {
        this.offset = offset;
        this.viewObserver = viewObserver;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x: " + (e.getX() - offset.getX()) + ", y: " + (e.getY() - offset.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vector pos = new Vector(e.getX() - offset.getX(), e.getY() - offset.getY());
        viewObserver.updateMousePosition(pos);
    }
}
