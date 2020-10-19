package view;

/**
 * @author Sebastian
 * Interface for objects that can shutdown.
 * Is used in applicationLoop, and implemented by view.
 */

@FunctionalInterface
public interface ShutDownAble {
    boolean isShutDown();
}
