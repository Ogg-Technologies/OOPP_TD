package view.particles;

import utils.Vector;

/**
 * Interface used by ParticleHandler to link event sender classes to a specific method in EmitterFactory using a HashMap
 */
@FunctionalInterface
interface EmitterCreator {
    Emitter createEmitter(Vector position);
}
