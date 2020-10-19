package view.particles.distribution;

/**
 * @author Oskar
 * Linear distribution with doubles.
 * Is used by EmitterFactory, Emitter and LinearVectorDistribution
 */
public class LinearDoubleDistribution implements Distribution<Double> {
    private final double min;
    private final double difference;

    private LinearDoubleDistribution(double min, double max) {
        this.min = min;
        difference = max - min;
    }

    public static LinearDoubleDistribution fromRange(double min, double max) {
        return new LinearDoubleDistribution(min, max);
    }

    public static LinearDoubleDistribution fromMidPoint(double midPoint, double spread) {
        return new LinearDoubleDistribution(midPoint - spread / 2, midPoint + spread / 2);
    }

    @Override
    public Double getRandom() {
        return min + (Math.random() * difference);
    }
}
