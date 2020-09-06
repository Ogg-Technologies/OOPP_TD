package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingMouseController implements MouseListener, MouseController {

    //TODO: have these offsets somewhere else?
    //These offsets makes so that the upper left usable window corner actually is 0, 0
    private final int xOffset = 8;
    private final int yOffset = 31;


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x: " + (e.getX() - xOffset) + ", y: " + (e.getY() - yOffset));
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
