package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingMouseController implements MouseListener, MouseController {
    private final int xOffset;
    private final int yOffset;
    public SwingMouseController(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

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
