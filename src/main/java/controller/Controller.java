package controller;

import model.ModelInputListener;
import view.gameView.IGameView;

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
     * @param IGameView               a view that need input from controller
     */
    public Controller(ModelInputListener modelInputListener, IGameView IGameView) {
        ControllerState controllerState = new ControllerState(modelInputListener.getFactory());
        SwingMouseController mouseController = new SwingMouseController(IGameView, IGameView, modelInputListener, controllerState);
        SwingKeyboardController keyboardController = new SwingKeyboardController(controllerState, IGameView.maxTowersInTowerPanel());
        IGameView.addMouseListener(mouseController);
        IGameView.addMouseMotionListener(mouseController);
        IGameView.addState(controllerState);
        IGameView.addButtonClickHandler(mouseController);
        IGameView.addKeyListener(keyboardController);
    }

}
