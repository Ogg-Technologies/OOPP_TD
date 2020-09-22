package view.particles.distribution;

import utils.VectorD;

public class LinearVectorDistribution implements Distribution<VectorD> {
    private final Distribution<Double> angle;
    private final Distribution<Double> magnitude;

    private LinearVectorDistribution(Distribution<Double> angle, Distribution<Double> magnitude) {
        this.angle = angle;
        this.magnitude = magnitude;
    }

    public static LinearVectorDistribution withAnyAngle(Distribution<Double> magnitude) {
        return new LinearVectorDistribution(LinearDoubleDistribution.fromMidPoint(0, Math.PI * 2), magnitude);
    }

    public static LinearVectorDistribution fromAngleAndMagnitude(Distribution<Double> angle, Distribution<Double> magnitude) {
        return new LinearVectorDistribution(angle, magnitude);
    }

    @Override
    public VectorD getRandom() {
        return VectorD.fromPolar(angle.getRandom(), magnitude.getRandom());
    }
}
