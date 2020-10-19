package view.gameView.layers;

import config.Config;
import model.ModelData;
import model.game.projectile.Projectile;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import view.WindowState;
import view.texture.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastian, Samuel, Erik
 * Display projectiles.
 * Is used by swingView.
 */
public class ProjectileDrawer extends JPanel {


    private final Map<Object, BufferedImage> imageMap = new HashMap<>();
    private final Map<Object, Double> sizeMap = new HashMap<>();
    private final ModelData modelData;
    private final WindowState windowState;
    private BufferedImage rockImage;
    private BufferedImage bombardaImage;

    public ProjectileDrawer(ModelData modelData, WindowState windowState) {
        this.windowState = windowState;
        this.modelData = modelData;
        setupImages();
        imageMap.put(Rock.class, rockImage);
        imageMap.put(BombardaCharm.class, bombardaImage);
        //Size parameter is percent of tileSize
        sizeMap.put(Rock.class, Config.INSTANCE.ROCK.SIZE);
        sizeMap.put(BombardaCharm.class, Config.INSTANCE.BOMBARDA_CHARM.SIZE);
    }

    private void setupImages() {
        rockImage = ImageHandler.getImage(Config.INSTANCE.IMAGE_PATH.ROCK);
        bombardaImage = ImageHandler.getImage(Config.INSTANCE.IMAGE_PATH.BOMBARDA_CHARM);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Collection<? extends Projectile> pList = modelData.getProjectiles();
        for (Projectile p : pList) {
            BufferedImage img = imageMap.get(p.getClass());
            int x = (int) ((p.getPosition().x + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().x);
            int y = (int) ((p.getPosition().y + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().y);
            int size = (int) (windowState.getTileSize() * sizeMap.get(p.getClass()));
            x -= size / 2;
            y -= size / 2;
            g.drawImage(img, x, y, size, size, null);
        }
    }
}
