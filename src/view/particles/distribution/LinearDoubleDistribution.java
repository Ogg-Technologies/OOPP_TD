package view.particles.distribution;

public class LinearDoubleDistribution implements Distribution<Double> {
    private final double min;
    private final double max;
    private final double difference;

    public LinearDoubleDistribution(double min, double max) {
        this.min = min;
        this.max = max;
        difference = max - min;
    }

    @Override
    public Double getRandom() {
        return min + (Math.random() * difference);
    }
}
