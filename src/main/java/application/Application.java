package application;

import controller.Controller;
import model.Model;
import model.game.map.TileMap;
import view.gameView.GameView;
import view.mainMenuView.MainMenuView;

import javax.swing.*;
import java.util.List;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * The Application class is what creates all necessary objects.
 */
public class Application {

    private List<? extends TileMap> maps;
    private final JFrame window;
    private Stoppable stoppable;

    /**
     * The constructor for Application which instantiate all the necessary objects.
     * It creates a JFrame and sets it up with standard values.
     */
    public Application() {
        maps = new MapLoader().loadMaps();

        window = new JFrame();
        window.setSize(800, 600);
        window.setVisible(true);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupMainMenuView();
    }

    private void setupGameView() {
        standardSetup();
        Model model = new Model(maps.get(0));
        GameView gameView = new GameView(window, model, this::setupMainMenuView);
        Controller controller = new Controller(model, gameView);
        ApplicationLoop loop = new ApplicationLoop(model, gameView);
        stoppable = loop;
        loop.start();
    }

    private void setupMainMenuView() {
        standardSetup();
        TileMap[] tileMapsArray = new TileMap[maps.size()];
        maps.toArray(tileMapsArray);
        MainMenuView mainMenuView = new MainMenuView(window, tileMapsArray, this::setupGameView);
        MainMenuLoop mainMenuLoop = new MainMenuLoop(mainMenuView);
        stoppable = mainMenuLoop;
        new Thread(mainMenuLoop).start();
    }

    private void standardSetup() {
        if (stoppable != null) {
            stoppable.stop();
        }
        stoppable = null;
    }

    /**
     * This is a class for having a loop for main menu
     */
    private static class MainMenuLoop implements Runnable, Stoppable {

        private volatile boolean shouldRun = true;
        private final MainMenuView mainMenuView;

        private MainMenuLoop(MainMenuView mainMenuView) {
            this.mainMenuView = mainMenuView;
        }

        @Override
        public void run() {
            while (shouldRun) {
                mainMenuView.draw();
            }
        }

        @Override
        public void stop() {
            shouldRun = false;
        }
    }

}
