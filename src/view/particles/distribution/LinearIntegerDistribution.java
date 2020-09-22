package view.particles.distribution;

public class LinearIntegerDistribution implements Distribution<Integer> {
    private final int min;
    private final int difference;

    private LinearIntegerDistribution(int min, int max) {
        this.min = min;
        this.difference = max - min;
    }

    public static LinearIntegerDistribution fromRange(int min, int max) {
        return new LinearIntegerDistribution(min, max);
    }

    @Override
    public Integer getRandom() {
        return (int) (min + (Math.random() * difference));
    }
}
