package application;

import controller.Controller;
import model.Model;
import view.SwingView;
import view.View;

public class Application implements ShutDownAble {

    ApplicationLoop loop;

    public Application() {
        Model model = new Model();
        View view = new SwingView(model, this);
        Controller controller = new Controller(model, view);

        view.start();
        loop = new ApplicationLoop(model, view);
        loop.start();
    }

    @Override
    public void shutDown() {
        loop.shutDown();
    }
}
