package view.gameView.layers;

import config.Config;
import model.ModelData;
import model.game.projectile.Projectile;
import model.game.projectile.concreteprojectile.*;
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
 * Displays projectiles.
 * Is used by swingView.
 */
public class ProjectileDrawer extends JPanel {

    private final Map<Class<? extends Projectile>, String> imageMap = new HashMap<>();
    private final Map<Class<? extends Projectile>, Double> sizeMap = new HashMap<>();
    private final ModelData modelData;
    private final WindowState windowState;

    /**
     * Sets up an imageMap for the images of all projectiles and a sizeMap for each projectile.
     * @param modelData Is used to get all current projectiles in the model.
     * @param windowState Is used to get information about the current window.
     */
    public ProjectileDrawer(ModelData modelData, WindowState windowState) {
        this.windowState = windowState;
        this.modelData = modelData;
        imageMap.put(Rock.class, Config.ImagePath.ROCK);
        imageMap.put(BombardaCharm.class, Config.ImagePath.BOMBARDA_CHARM);
        imageMap.put(Rocket.class, Config.ImagePath.ROCKET);
        imageMap.put(Bee.class, Config.ImagePath.BEE);
        //Size parameter is percent of tileSize
        sizeMap.put(Rock.class, Config.Rock.SIZE);
        sizeMap.put(BombardaCharm.class, Config.BombardaCharm.SIZE);
        sizeMap.put(Rocket.class, Config.Rocket.SIZE);
        sizeMap.put(Bee.class, Config.Bee.SIZE);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Collection<? extends Projectile> pList = modelData.getProjectiles();
        for (Projectile p : pList) {
            paintProjectile(p, g);
        }
    }

    private void paintProjectile(Projectile p, Graphics g) {
        final double sin = Math.abs(Math.sin(p.getVelocity().getAngle()));
        final double cos = Math.abs(Math.cos(p.getVelocity().getAngle()));

        BufferedImage img = ImageHandler.getImage(imageMap.get(p.getClass()), p.getVelocity().getAngle());

        int x = (int) ((p.getPosition().x + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().x);
        int y = (int) ((p.getPosition().y + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().y);

        double size = (windowState.getTileSize() * cos + windowState.getTileSize() * sin) * sizeMap.get(p.getClass());

        x -= size / 2;
        y -= size / 2;

        g.drawImage(img, x, y, (int) size, (int) size, null);
    }

}
