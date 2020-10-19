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

    List<? extends TileMap> maps;
    private final JFrame window;

    /**
     * The constructor for Application which instantiate all the necessary objects.
     */
    public Application() {
        maps = new MapLoader().loadMaps();

        window = new JFrame();
        window.setSize(800, 600);
        window.setVisible(true);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupGameView();
    }

    private void setupGameView() {
        Model model = new Model(maps.get(0));
        GameView gameView = new GameView(window, model);
        Controller controller = new Controller(model, gameView);
        ApplicationLoop loop = new ApplicationLoop(model, gameView);
        loop.start();
    }

    private void setupMainMenuView() {
        TileMap[] tileMapsArray = new TileMap[maps.size()];
        maps.toArray(tileMapsArray);
        MainMenuView mainMenuView = new MainMenuView(window, tileMapsArray);
        new Thread(() -> {
            while (true) {
                mainMenuView.draw();
            }
        }).start();
    }

}
