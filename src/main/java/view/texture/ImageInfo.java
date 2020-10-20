package view.texture;

import java.util.Objects;

/**
 * @author Oskar, Samuel
 * Saves a path and an angle together, to check if the correlation exists in imageHandler
 * Rounds angle to a nearby number to avoid too much precision in angles
 */
public class ImageInfo {
    /**
     * Divides the 2*PI of a circle into this many discrete angles. All angles get rounded to the closest one.
     * A high value here means more precision in rotated images. A lower value means better performance.
     */
    public static final int NUMBER_OF_POSSIBLE_ANGLES = 100;
    public final String path;
    public final double angle;

    public ImageInfo(String path, double angle) {
        if (path == null) {
            throw new IllegalArgumentException("path may not be null");
        }
        this.path = path;
        this.angle = adjustAngle(angle);
    }

    /**
     * First removes any extra rotations in the angle.
     * Then it rounds the angle to the closest one of the evenly distributed NUMBER_OF_POSSIBLE_ANGLES.
     *
     * @param angle the angle to round
     * @return the rounded angle
     */
    private double adjustAngle(double angle) {
        double circleFraction = angle / (Math.PI * 2);
        circleFraction = circleFraction % 1;

        double roundedCircleFraction = Math.round(circleFraction * NUMBER_OF_POSSIBLE_ANGLES) / (double) NUMBER_OF_POSSIBLE_ANGLES;
        double newAngle = roundedCircleFraction * (Math.PI * 2);

        return newAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageInfo imageInfo = (ImageInfo) o;
        return Double.compare(imageInfo.angle, angle) == 0 &&
                path.equals(imageInfo.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, angle);
    }
}
