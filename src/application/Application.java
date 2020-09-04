package application;

import controller.Controller;
import model.Model;
import view.SwingView;
import view.View;

public class Application {
    public Application() {
        Model model = new Model();
        View view = new SwingView(model);
        Controller controller = new Controller(model, view);

        view.start();
        ApplicationLoop loop = new ApplicationLoop(model, view);
        loop.start();
    }
}
