package view.particles.emitterdata;

import utils.VectorF;

import java.awt.*;

public interface EmitterData {
    int getEmitterLifetime();
    double newParticlesPerUpdate();

    int getNewParticleLifetime();
    VectorF getNewStartPosition();
    VectorF getNewStartVelocity();
    double getNewAngleVelocity();
    double getNewTileSize();
    double getNewFriction();
    Image getImage();
}
