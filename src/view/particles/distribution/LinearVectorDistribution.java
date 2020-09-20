package view.particles.distribution;

import utils.VectorF;

public class LinearVectorDistribution implements Distribution<VectorF> {
    private final VectorF min;
    private final VectorF difference;

    public LinearVectorDistribution(VectorF min, VectorF max) {
        this.min = min;
        this.difference = max.minus(min);
    }

    // TODO: Fix so that random vector is within circle instead of rectangle (possibly other class?)
    @Override
    public VectorF getRandom() {
        float x = (float) (min.getX() + Math.random() * difference.getX());
        float y = (float) (min.getY() + Math.random() * difference.getY());
        return new VectorF(x, y);
    }
}
