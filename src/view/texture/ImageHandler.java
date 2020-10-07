package view.texture;

import application.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oskar, Samuel, Erik
 * Saves images for a while, and deletes them when probably obsolete
 * Is used by towerHandler
 */
public final class ImageHandler {
    private static final int ROTATED_IMAGES_CACHE_SIZE = Constant.getInstance().ROTATED_IMAGE_CACHE_SIZE;

    private static final Map<String, BufferedImage> images = new LinkedHashMap<>();
    private static final Map<ImageInfo, BufferedImage> rotatedImages = new LinkedHashMap<>();

    /**
     * Fetches a reference to the given image from cache if it exists,
     * else it loads it and puts it in the list of loaded images
     *
     * @param path  The path to the image
     * @param angle The angle
     * @return A reference to the image with the correct angle
     */
    public static BufferedImage getImage(String path, double angle) {
        discardOldRotatedImagesIfFull();

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

    /**
     * Fetches a reference to the given image from cache if it exists,
     * else it loads it and puts it in the list of loaded images
     *
     * @param path The path to the image
     * @return A reference to the image
     */
    public static BufferedImage getImage(String path) {
        if (images.containsKey(path)) {
            return images.get(path);
        }

        BufferedImage image = readImageFromFile(path);
        images.put(path, image);
        return image;
    }

    /**
     * Continuously removes the first image in the list to avoid memory leak
     */
    private static void discardOldRotatedImagesIfFull() {
        while (rotatedImages.size() > ROTATED_IMAGES_CACHE_SIZE) {
            ImageInfo first = rotatedImages.keySet().iterator().next();
            rotatedImages.remove(first);
        }
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
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2.0, h / 2.0);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}