package view.particles.distribution;

/**
 * @author Oskar
 * Specialized random generator for parameter.
 * Is used by Emitter.
 * @param <T> what type to distribute
 */
@FunctionalInterface
public interface Distribution<T> {
    T getRandom();
}
