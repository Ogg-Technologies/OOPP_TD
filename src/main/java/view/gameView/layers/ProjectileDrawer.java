package view.gameView.layers;

import config.Config;
import model.ModelData;
import model.game.projectile.Projectile;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import model.game.projectile.concreteprojectile.Rocket;
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


    private final Map<Class<? extends Projectile>, String> imageMap = new HashMap<>();
    private final Map<Class<? extends Projectile>, Double> sizeMap = new HashMap<>();
    private final ModelData modelData;
    private final WindowState windowState;
    private String rockImagePath;
    private String bombardaImagePath;
    private String rocketImagePath;

    public ProjectileDrawer(ModelData modelData, WindowState windowState) {
        this.windowState = windowState;
        this.modelData = modelData;
        imageMap.put(Rock.class, Config.ImagePath.ROCK);
        imageMap.put(BombardaCharm.class, Config.ImagePath.BOMBARDA_CHARM);
        imageMap.put(Rocket.class, Config.ImagePath.ROCKET);
        //Size parameter is percent of tileSize
        sizeMap.put(Rock.class, Config.Rock.SIZE);
        sizeMap.put(BombardaCharm.class, Config.BombardaCharm.SIZE);
        sizeMap.put(Rocket.class, Config.Rocket.SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Collection<? extends Projectile> pList = modelData.getProjectiles();
        for (Projectile p : pList) {
            BufferedImage img = ImageHandler.getImage(imageMap.get(p.getClass()), p.getVelocity().getAngle());
            int x = (int) ((p.getPosition().x + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().x);
            int y = (int) ((p.getPosition().y + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().y);
            int size = (int) (windowState.getTileSize() * sizeMap.get(p.getClass()));
            x -= size / 2;
            y -= size / 2;
            g.drawImage(img, x, y, size, size, null);
        }
    }
}
