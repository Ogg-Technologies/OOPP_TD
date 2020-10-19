package view;

/**
 * @author Sebastian
 * This interface is for when a view component is supposed to close, and should call this method when closing
 * It is used in Application and View
 */
@FunctionalInterface
public interface OnClose {
    /**
     * The method to call when view is closing
     */
    void close();
}
