package model.particles;

import utils.VectorF;

/**
 * Observer for model to tell when and where particles should be created
 */
public interface EmitterCreator {

    /**
     * Used by model to notify when and where a particle emitter should be created
     * @param type The particle effect type
     * @param position The grid position of the particle emitter
     */
    void createEmitter(ParticleType type, VectorF position);
}
