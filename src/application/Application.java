package application;

import controller.Controller;
import model.Model;
import view.SwingView;
import view.View;

/**
 * The Application class is what creates all necessary objects.
 */
public class Application {

    /**
     * The constructor for Application which instantiate all the necessary objects.
     */
    public Application() {
        Model model = new Model();
        View view = new SwingView(model);
        Controller controller = new Controller(model, view);
        view.start();

        ApplicationLoop loop = new ApplicationLoop(model, view, view);
        loop.start();
    }

}
