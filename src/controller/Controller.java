package controller;

import model.ModelEventHandler;
import view.View;

public class Controller {
    public Controller(ModelEventHandler modelEventHandler, View view) {
        SwingMouseController mouseController = new SwingMouseController();
        view.addMouseListener(mouseController);
    }
}
