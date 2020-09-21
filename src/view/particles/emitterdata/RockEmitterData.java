package view.particles.emitterdata;

import utils.VectorD;
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
    private Distribution<VectorD> startPositionDistribution =
            new LinearVectorDistribution(new VectorD(-1, -1), new VectorD(1, 1));
    //new LinearVectorDistribution(new VectorD(0, 0), new VectorD(0, 0));
    private Distribution<VectorD> startVelocityDistribution =
            new LinearVectorDistribution(new VectorD(-0.02, -0.02), new VectorD(0.02, 0.02));

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
    public VectorD getNewStartPosition() {
        return startPositionDistribution.getRandom();
    }

    @Override
    public VectorD getNewStartVelocity() {
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
