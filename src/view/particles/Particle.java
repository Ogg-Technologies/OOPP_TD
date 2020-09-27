package view.particles;

import utils.VectorD;
import view.WindowState;

import java.awt.*;

public class Particle {

    private int lifetime;
    private VectorD tilePosition;
    private VectorD velocity;
    private double angle; // TODO: Support angles
    private double angleVelocity;

    private final double tileSize;
    private final double friction;
    private final Image image;

    public Particle(int lifetime, VectorD tilePosition, VectorD velocity, double angleVelocity,
                    double tileSize, double friction, Image image) {
        this.lifetime = lifetime;
        this.tilePosition = tilePosition;
        this.velocity = velocity;
        this.angleVelocity = angleVelocity;
        this.tileSize = tileSize;
        this.friction = friction;
        this.image = image;
        angle = randomAngle();
    }

    public void update() {
        if (isDead()) {
            return;
        }
        lifetime--;

        velocity = velocity.times(friction);
        tilePosition = tilePosition.plus(velocity);
        angle += angleVelocity;
    }

    public void draw(Graphics graphics, WindowState windowState) {
        int x = (int) ((tilePosition.x + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().getX());
        int y = (int) ((tilePosition.y + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().getY());
        int size = (int) (this.tileSize * windowState.getTileSize());
        graphics.drawImage(image, x, y, size, size, null);
    }

    public boolean isDead() {
        return lifetime <= 0;
    }

    private static double randomAngle() {
        return Math.random() * 2 * Math.PI;
    }
}
