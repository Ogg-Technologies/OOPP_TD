package view.particles.distribution;

@FunctionalInterface
public interface Distribution<T> {
    T getRandom();
}
