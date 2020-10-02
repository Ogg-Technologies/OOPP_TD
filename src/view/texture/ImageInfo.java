package view.texture;

import java.util.Objects;

/**
 * @author Oskar, Samuel
 * Saves a path and an angle together, to check if the correlation exists in imageHandler
 */
public class ImageInfo {
    public final String path;
    public final double angle;

    public ImageInfo(String path, double angle) {
        if (path == null) {
            throw new IllegalArgumentException("path may not be null");
        }
        this.path = path;
        this.angle = angle;
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
