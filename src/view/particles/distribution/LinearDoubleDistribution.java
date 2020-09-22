package view.particles.distribution;

public class LinearDoubleDistribution implements Distribution<Double> {
    private final double min;
    private final double max;
    private final double difference;

    private LinearDoubleDistribution(double min, double max) {
        this.min = min;
        this.max = max;
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
