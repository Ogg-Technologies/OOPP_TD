package view.particles.distribution;

import utils.VectorD;

public class LinearVectorDistribution implements Distribution<VectorD> {
    private final VectorD min;
    private final VectorD difference;

    public LinearVectorDistribution(VectorD min, VectorD max) {
        this.min = min;
        this.difference = max.minus(min);
    }

    // TODO: Fix so that random vector is within circle instead of rectangle (possibly other class?)
    @Override
    public VectorD getRandom() {
        double x = min.getX() + Math.random() * difference.getX();
        double y = min.getY() + Math.random() * difference.getY();
        return new VectorD(x, y);
    }
}
