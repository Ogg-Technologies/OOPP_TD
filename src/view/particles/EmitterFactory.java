package view.particles;

import utils.Vector;
import view.particles.distribution.LinearDoubleDistribution;
import view.particles.distribution.LinearIntegerDistribution;
import view.particles.distribution.LinearVectorDistribution;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

/**
 * @author Samuel, Erik
 * Factory for creating particle emitters
 * Is used by particleHandler
 */
public final class EmitterFactory {

    private EmitterFactory() {
    }

    private static Image rockImage;
    private static Image boltImage;
    private static Image sniperSmokeImage;
    private static Image bulletImage;
    private static Image axeImage;

    static {
        try {
            rockImage = ImageIO.read(new File("resource/stone.png"));
            boltImage = ImageIO.read(new File("resource/bombardaCharm.png"));
            sniperSmokeImage = ImageIO.read(new File("resource/sniperSmoke.png"));
            bulletImage = ImageIO.read(new File("resource/bullet.png"));
            axeImage = ImageIO.read(new File("resource/axe.png"));

        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    static Emitter createRockEmitter(Vector position) {
        return new Emitter.Builder()
                .setEmitterPosition(position)
                .setImage(rockImage)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(10, 20))
                .setStartPosition(LinearVectorDistribution.withAnyAngle(() -> 0.1))
                .setStartVelocity(LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0.001, 0.02)))
                .setEmitterLifetime(10)
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(0, 3))
                .build();
    }

    static Emitter createBombardaEmitter(Vector position) {
        return new Emitter.Builder()
                .setEmitterPosition(position)
                .setImage(boltImage)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(10, 20))
                .setStartPosition(LinearVectorDistribution.withAnyAngle(() -> 0.3))
                .setStartVelocity(LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0.02, 0.03)))
                .setEmitterLifetime(3)
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(4, 10))
                .setTileSize(LinearDoubleDistribution.fromRange(0.05, 0.2))
                .build();
    }

    static Emitter createSniperSmokeEmitter(Vector position) { //TODO make actuall effect that had the correct angle etc...
        return new Emitter.Builder()
                .setEmitterPosition(position)
                .setImage(sniperSmokeImage)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(30, 30))
                .setStartPosition(LinearVectorDistribution.withAnyAngle(() -> 0.3))
                .setStartVelocity(LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0.001, 0.01)))
                .setEmitterLifetime(1)
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(10, 10))
                .setTileSize(LinearDoubleDistribution.fromRange(0.05, 0.3))
                .build();
    }

    public static Emitter createBulletEmitter(Vector position) {
        return new Emitter.Builder()
                .setEmitterPosition(position)
                .setImage(bulletImage)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(15, 15))
                .setStartPosition(LinearVectorDistribution.withAnyAngle(() -> 0.3))
                .setStartVelocity(LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0.001, 0.01)))
                .setEmitterLifetime(1)
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(1, 1))
                .setTileSize(LinearDoubleDistribution.fromRange(0.05, 0.05))
                .build();
    }

    public static Emitter createSwingEmitter(Vector position) {
        return new Emitter.Builder()
                .setEmitterPosition(position)
                .setImage(axeImage)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(20, 20))
                .setStartPosition(LinearVectorDistribution.withAnyAngle(() -> 0.5))
                .setStartVelocity(LinearVectorDistribution.withAnyAngle(LinearDoubleDistribution.fromRange(0.001, 0.01)))
                .setEmitterLifetime(1)
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(1, 1))
                .setTileSize(LinearDoubleDistribution.fromRange(0.3, 0.3))
                .build();
    }
}
