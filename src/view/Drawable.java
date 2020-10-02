package view;

/**
 * @author Erik
 * An interface for objects that need to update/draw in some time intervall.
 * It is used by View and ApplicationLoop calls draw.
 */
@FunctionalInterface
public interface Drawable {
    void draw();
}
