package view;

/**
 * @author Sebastian
 * This interface is for calling when a button is clicked for closing all current elements.
 * This interface also takes in an index, where the index decides which map should be loaded in next iteration of
 * elements that will show up after these elements has closed.
 * It is used in view and application
 */
@FunctionalInterface
public interface OnCloseWithIndex {
    /**
     * The close call, that is suppose to give all these elements to the garbage collector
     *
     * @param index the index for the new map that should be played
     */
    void close(int index);
}
