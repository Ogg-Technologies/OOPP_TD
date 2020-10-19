package application;

/**
 * @author Sebastian
 * This interface is for loops that can stop. It is only used in Application
 */
@FunctionalInterface
public interface Stoppable {
    /**
     * The method to call for the loop to stop
     */
    void stop();
}
