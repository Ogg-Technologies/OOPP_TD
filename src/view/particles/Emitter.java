package view.particles;

import utils.VectorD;
import view.WindowState;
import view.particles.distribution.Distribution;
import view.particles.distribution.LinearDoubleDistribution;
import view.particles.distribution.LinearIntegerDistribution;
import view.particles.distribution.LinearVectorDistribution;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An Emitter is a position where particles are emitted from and are created from its inner Builder class.
 */
public class Emitter {

    /**
     * Fields describing the Emitter
     */
    private final VectorD emitterPosition;
    private int emitterLifetime;

    /**
     * Fields describing the particles the Emitter emits. Distribution class is used to make values randomised
     */
    private final Distribution<Integer> lifetime;
    private final Distribution<VectorD> startPosition;
    private final Distribution<VectorD> startVelocity;
    private final Distribution<Integer> newParticlesPerUpdate;
    private final Distribution<Double> angleVelocity;
    private final Distribution<Double> tileSize;
    private final Distribution<Double> friction;
    private final Image image;

    private final List<Particle> particles;

    /** Creates an Emitter from a Builder. Private constructor to force use of Builder */
    private Emitter(Builder builder) {
        this.emitterPosition = builder.emitterPosition;
        this.emitterLifetime = builder.emitterLifetime;

        this.lifetime = builder.lifetime;
        this.startPosition = builder.startPosition;
        this.startVelocity = builder.startVelocity;
        this.newParticlesPerUpdate = builder.newParticlesPerUpdate;
        this.angleVelocity = builder.angleVelocity;
        this.tileSize = builder.tileSize;
        this.friction = builder.friction;
        this.image = builder.image;

        this.particles = new ArrayList<>();
    }

    /**
     * Creates new particles if the emitter lifetime > 0, then updates each particle.
     * If the particle is dead it is removed
     */
    public void update() {
        if (emitterLifetime > 0) {
            for (int i = 0; i < newParticlesPerUpdate.getRandom(); i++) {
                createNewParticle();
            }
            emitterLifetime--;
        }
        for (Iterator<Particle> iterator = particles.iterator(); iterator.hasNext(); ) {
            Particle p = iterator.next();
            p.update();
            if (p.isDead()) {
                iterator.remove();
            }
        }
    }

    private void createNewParticle() {
        particles.add(new Particle(
                lifetime.getRandom(),
                emitterPosition.plus(startPosition.getRandom()),
                startVelocity.getRandom(),
                angleVelocity.getRandom(),
                tileSize.getRandom(),
                friction.getRandom(),
                image
        ));
    }

    /** Draws each particle on the screen */
    public void draw(Graphics graphics, WindowState windowState) {
        for (Particle p : particles) {
            p.draw(graphics, windowState);
        }
    }

    /**
     * @return True if the emitter is still active, meaning still creating particles or having particles that are still
     * alive, false otherwise. When false is returned the purpose of the emitter is finished and it can be removed
     */
    public boolean isAlive() {
        return emitterLifetime > 0 || !particles.isEmpty();
    }

    /**
     * Inner class Builder for building instances of Emitters. Every attribute not set when building will use the
     * default value with the exception of emitter position and image which must be set (otherwise it throws an exception)
     */
    public static class Builder {
        private int emitterLifetime = 10;
        private VectorD emitterPosition;
        private Distribution<Integer> lifetime = LinearIntegerDistribution.fromRange(5, 15);
        private Distribution<VectorD> startPosition =
                LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0, 0));
        private Distribution<VectorD> startVelocity =
                LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromMidPoint(0, 0.075));
        private Distribution<Integer> newParticlesPerUpdate = LinearIntegerDistribution.fromRange(3, 5);
        private Distribution<Double> angleVelocity = LinearDoubleDistribution.fromRange(-0.1, 0.1);
        private Distribution<Double> tileSize = LinearDoubleDistribution.fromRange(0.02, 0.1);
        private Distribution<Double> friction = LinearDoubleDistribution.fromRange(0.98, 0.99);
        private Image image;

        /**
         * Builds an emitter from the attributes given set in the builder. If position or image has not been set it will
         * throw exceptions
         * @return An Emitter
         */
        public Emitter build() {
            if (emitterPosition == null) {
                throw new RuntimeException(this.getClass().getSimpleName()
                        + " must have emitter position set before building " + Emitter.class.getSimpleName());
            }
            if (image == null) {
                throw new RuntimeException(this.getClass().getSimpleName()
                        + " must have image set before building " + Emitter.class.getSimpleName());
            }
            return new Emitter(this);
        }

        public Builder setEmitterLifetime(int emitterLifetime) {
            this.emitterLifetime = emitterLifetime;
            return this;
        }

        public Builder setEmitterPosition(VectorD emitterPosition) {
            this.emitterPosition = emitterPosition;
            return this;
        }

        public Builder setLifetimeDistribution(Distribution<Integer> lifetime) {
            this.lifetime = lifetime;
            return this;
        }

        public Builder setStartPosition(Distribution<VectorD> startPosition) {
            this.startPosition = startPosition;
            return this;
        }

        public Builder setStartVelocity(Distribution<VectorD> startVelocity) {
            this.startVelocity = startVelocity;
            return this;
        }

        public Builder setNewParticlesPerUpdate(Distribution<Integer> newParticlesPerUpdate) {
            this.newParticlesPerUpdate = newParticlesPerUpdate;
            return this;
        }

        public Builder setAngleVelocity(Distribution<Double> angleVelocity) {
            this.angleVelocity = angleVelocity;
            return this;
        }

        public Builder setTileSize(Distribution<Double> tileSize) {
            this.tileSize = tileSize;
            return this;
        }

        public Builder setFriction(Distribution<Double> friction) {
            this.friction = friction;
            return this;
        }

        public Builder setImage(Image image) {
            this.image = image;
            return this;
        }
    }
}
