package controller;

import model.ModelEventHandler;
import utils.Vector;
import view.View;

public class Controller implements ControlFacade{
    private View view;

    public Controller(ModelEventHandler modelEventHandler, View view) {
        SwingMouseController mouseController = new SwingMouseController(this, view, modelEventHandler);
        view.addMouseListener(mouseController);
        view.addMouseMotionListener(mouseController);
        this.view = view;
    }

    @Override
    public Vector convertFromRealPosToTilePos(Vector realPos) {
        return view.convertRealPosToTilePos(realPos);
    }

    @Override
    public Vector getOffset(){
        return view.getOffset();
    }
}
