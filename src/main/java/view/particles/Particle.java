package view.particles;

import utils.Vector;
import view.WindowState;
import view.texture.ImageHandler;

import java.awt.*;

/**
 * @author Erik, Oskar
 * A Particle is a small image that are usually clustered together with changes in attributes to make a visually
 * appealing effect. Every particle is created from an Emitter
 * Is used by Emitter.
 */
class Particle {

    private int lifetime;
    private Vector tilePosition;
    private Vector velocity;
    private final String imagePath;
    private double angleVelocity;

    private final double tileSize;
    private final double friction;
    private double angle;

    public Particle(int lifetime, Vector tilePosition, Vector velocity, double angle, double angleVelocity,
                    double tileSize, double friction, String imagePath) {
        this.lifetime = lifetime;
        this.tilePosition = tilePosition;
        this.velocity = velocity;
        this.angle = angle;
        this.angleVelocity = angleVelocity;
        this.tileSize = tileSize;
        this.friction = friction;
        this.imagePath = imagePath;
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
        int size = (int) (this.tileSize * windowState.getTileSize());
        int x = (int) ((tilePosition.x + 0.5) * windowState.getTileSize() - size/2 + windowState.getTileMapOffset().x);
        int y = (int) ((tilePosition.y + 0.5) * windowState.getTileSize() - size/2 + windowState.getTileMapOffset().y);
        graphics.drawImage(ImageHandler.getImage(imagePath, angle), x, y, size, size, null);
    }

    /** @return True if the particle is dead, meaning it should be removed */
    public boolean isDead() {
        return lifetime <= 0;
    }

    private static double randomAngle() {
        return Math.random() * 2 * Math.PI;
    }
}
