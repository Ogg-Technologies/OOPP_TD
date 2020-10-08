package controller;

import model.ModelInputListener;
import view.View;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * This class is setting up all the other, more specific, controllers.
 */
public class Controller {
    /**
     * Initiate all the controllers and sets up all listeners to view.
     * Is used by Application.
     *
     * @param modelInputListener handler of events for model
     * @param view               a view that need input from controller
     */
    public Controller(ModelInputListener modelInputListener, View view) {
        ControllerState controllerState = new ControllerState(modelInputListener.getFactory());
        SwingMouseController mouseController = new SwingMouseController(view, view, modelInputListener, controllerState);
        view.addMouseListener(mouseController);
        view.addMouseMotionListener(mouseController);
        view.addState(controllerState);
        view.addButtonClickHandler(mouseController);
    }

}
