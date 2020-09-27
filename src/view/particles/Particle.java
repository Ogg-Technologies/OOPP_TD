package view.particles;

import utils.Vector;
import view.WindowState;

import java.awt.*;

/**
 * A Particle is a small image that are usually clustered together with changes in attributes to make a visually
 * appealing effect. Every particle is created from an Emitter
 */
class Particle {

    private int lifetime;
    private Vector tilePosition;
    private Vector velocity;
    private double angle; // TODO: Support angles
    private double angleVelocity;

    private final double tileSize;
    private final double friction;
    private final Image image;

    public Particle(int lifetime, Vector tilePosition, Vector velocity, double angleVelocity,
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
        int x = (int) ((tilePosition.x + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().x);
        int y = (int) ((tilePosition.y + 0.5) * windowState.getTileSize() + windowState.getTileMapOffset().y);
        int size = (int) (this.tileSize * windowState.getTileSize());
        graphics.drawImage(image, x, y, size, size, null);
    }

    /** @return True if the particle is dead, meaning it should be removed */
    public boolean isDead() {
        return lifetime <= 0;
    }

    private static double randomAngle() {
        return Math.random() * 2 * Math.PI;
    }
}
