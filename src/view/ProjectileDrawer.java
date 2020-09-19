package view;

import model.ModelData;
import model.game.projectile.Projectile;
import model.game.projectile.concreteprojectile.Rock;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectileDrawer extends JPanel {


    private final Map<Object, BufferedImage> imageMap = new HashMap<>();
    private final Map<Object, Double> sizeMap = new HashMap<>();
    private final ModelData modelData;
    private final WindowState windowState;
    private BufferedImage rockImage;

    public ProjectileDrawer(ModelData modelData, WindowState windowState) {
        this.windowState = windowState;
        this.modelData = modelData;
        setupImages();
        imageMap.put(Rock.class, rockImage);
        sizeMap.put(Rock.class, 0.25);
    }

    private void setupImages() {
        try {
            rockImage = ImageIO.read(new File("resource/stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<? extends Projectile> pList = modelData.getProjectiles();
        for (Projectile p : pList) {
            BufferedImage img = imageMap.get(p.getClass());
            int x = (int) ((p.getPosition().getX() + 0.5) * windowState.getTileSize() + windowState.getOffset().getX());
            int y = (int) ((p.getPosition().getY() + 0.5) * windowState.getTileSize() + windowState.getOffset().getY());
            int size = (int) (windowState.getTileSize() * sizeMap.get(p.getClass()));
            g.drawImage(img, x, y, size, size, null);
        }
    }
}
