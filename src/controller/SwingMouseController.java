package controller;

import model.ModelEventHandler;
import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SwingMouseController implements MouseListener, MouseMotionListener, MouseController {

    private final ControlFacade facade;

    private final MouseViewObserver viewObserver;

    private final ModelEventHandler modelEventHandler;

    public SwingMouseController(ControlFacade facade, MouseViewObserver viewObserver, ModelEventHandler modelEventHandler) {
        this.facade = facade;
        this.viewObserver = viewObserver;
        this.modelEventHandler = modelEventHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x: " + (e.getX() - facade.getOffset().getX()) + ", y: " + (e.getY() - facade.getOffset().getY()));
        modelEventHandler.clickedTile(facade.convertFromRealPosToTilePos(new Vector(e.getX() - facade.getOffset().getX(), e.getY() - facade.getOffset().getY())));
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
        Vector pos = new Vector(e.getX() - facade.getOffset().getX(), e.getY() - facade.getOffset().getY());
        viewObserver.updateMousePosition(pos);
    }
}
