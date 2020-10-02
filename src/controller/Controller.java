package controller;

import model.ModelInputListener;
import view.View;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * This class is setting up all the other, more specific, controllers.
 */
public class Controller {
    /**
     * Initiate all the controllers
     *
     * @param modelInputListener handler of events for model
     * @param view               a view that need input from controller
     */
    public Controller(ModelInputListener modelInputListener, View view) {
        SwingMouseController mouseController = new SwingMouseController(view, view, modelInputListener);
        view.addMouseListener(mouseController);
        view.addMouseMotionListener(mouseController);
    }

}
