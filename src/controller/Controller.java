package controller;

import model.ModelEventHandler;
import view.View;

/**
 * This class is setting up all the other, more specific, controllers.
 */
public class Controller {
    /**
     * Initiate all the controllers
     *
     * @param modelEventHandler handler of events for model
     * @param view              a view that need input from controller
     */
    public Controller(ModelEventHandler modelEventHandler, View view) {
        SwingMouseController mouseController = new SwingMouseController(view, view, modelEventHandler);
        view.addMouseListener(mouseController);
        view.addMouseMotionListener(mouseController);
    }

}
