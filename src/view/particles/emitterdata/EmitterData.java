package view.particles.emitterdata;

import utils.VectorD;

import java.awt.*;

public interface EmitterData {
    int getEmitterLifetime();

    double newParticlesPerUpdate();

    int getNewParticleLifetime();

    VectorD getNewStartPosition();

    VectorD getNewStartVelocity();

    double getNewAngleVelocity();

    double getNewTileSize();

    double getNewFriction();

    Image getImage();
}
