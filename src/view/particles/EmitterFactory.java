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
 * Factory for creating particle emitters
 */
public final class EmitterFactory {

    private EmitterFactory() { }

    private static Image rockImage;

    static {
        try {
            rockImage = ImageIO.read(new File("resource/stone.png"));
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
}
