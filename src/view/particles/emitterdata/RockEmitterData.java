package view.particles.emitterdata;

import utils.VectorF;
import view.particles.distribution.Distribution;
import view.particles.distribution.LinearIntegerDistribution;
import view.particles.distribution.LinearVectorDistribution;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RockEmitterData implements EmitterData {   // TODO: Test particle, will probably be removed later

    private static Image image;

    static {
        try {
            image = ImageIO.read(new File("resource/stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Distribution<Integer> lifetimeDistribution = new LinearIntegerDistribution(30, 60);
    private Distribution<VectorF> startPositionDistribution =
            new LinearVectorDistribution(new VectorF(-1, -1), new VectorF(1, 1));
            //new LinearVectorDistribution(new VectorF(0, 0), new VectorF(0, 0));
    private Distribution<VectorF> startVelocityDistribution =
            new LinearVectorDistribution(new VectorF(-0.02f, -0.02f), new VectorF(0.02f, 0.02f));

    @Override
    public int getEmitterLifetime() {
        return 10;
    }

    @Override
    public double newParticlesPerUpdate() {
        return 2;
    }

    @Override
    public int getNewParticleLifetime() {
        return lifetimeDistribution.getRandom();
    }

    @Override
    public VectorF getNewStartPosition() {
        return startPositionDistribution.getRandom();
    }

    @Override
    public VectorF getNewStartVelocity() {
        return startVelocityDistribution.getRandom();
    }

    @Override
    public double getNewAngleVelocity() {
        return 0;
    }

    @Override
    public double getNewTileSize() {
        return 0.1;
    }

    @Override
    public double getNewFriction() {
        return 0.99;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
