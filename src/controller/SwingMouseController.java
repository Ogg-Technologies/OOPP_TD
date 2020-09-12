package controller;

import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingMouseController implements MouseListener, MouseController {

    private final Vector offset;

    public SwingMouseController(Vector offset) {
        this.offset = offset;
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
}
