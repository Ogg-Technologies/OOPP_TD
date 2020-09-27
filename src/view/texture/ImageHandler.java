package view.texture;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ImageHandler {
    // TODO: periodically remove old images from both images and rotatedImages. Currently this class creates a memory leak.
    // We might be able to remove images if we store the base images in rotatedImages with angle=0?

    private static Map<String, BufferedImage> images = new LinkedHashMap<>();
    private static Map<ImageInfo, BufferedImage> rotatedImages = new LinkedHashMap<>();

    public static BufferedImage getImage(String path, double angle) {
        if (angle == 0) {
            return getImage(path);
        }

        ImageInfo key = new ImageInfo(path, angle);
        if (rotatedImages.containsKey(key)) {
            return rotatedImages.get(key);
        }

        BufferedImage baseImage = getImage(path);

        BufferedImage rotatedImage = rotate(baseImage, angle);
        rotatedImages.put(key, rotatedImage);

        return rotatedImage;
    }

    public static BufferedImage getImage(String path) {
        if (images.containsKey(path)) {
            return images.get(path);
        }

        BufferedImage image = readImageFromFile(path);
        images.put(path, image);
        return image;
    }

    private static BufferedImage readImageFromFile(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    private static BufferedImage rotate(BufferedImage image, double angle) {
        final double sin = Math.abs(Math.sin(angle));
        final double cos = Math.abs(Math.cos(angle));
        final int width = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int height = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(width, height, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(width / 2, height / 2);
        at.rotate(angle, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }
}