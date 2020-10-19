package application;

import controller.Controller;
import model.Model;
import model.game.map.TileMap;
import view.SwingView;
import view.View;

import java.util.List;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * The Application class is what creates all necessary objects.
 */
public class Application {

    /**
     * The constructor for Application which instantiate all the necessary objects.
     */
    public Application() {
        List<? extends TileMap> maps = new MapLoader().loadMaps();

        Model model = new Model(maps);
        View view = new SwingView(model);
        Controller controller = new Controller(model, view);
        view.start();

        ApplicationLoop loop = new ApplicationLoop(model, view, view);
        loop.start();
    }

}
