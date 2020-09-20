package view.particles.distribution;

public class LinearIntegerDistribution implements Distribution<Integer> {
    private final int min;
    private final int difference;

    public LinearIntegerDistribution(int min, int max) {
        this.min = min;
        this.difference = max - min;
    }

    @Override
    public Integer getRandom() {
        return (int) (min + (Math.random() * difference));
    }
}
