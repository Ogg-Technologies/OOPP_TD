package view;

/**
 * @author Sebastian
 * This method is for when a button is clicked for changing new state.
 * It is only used in View
 */
@FunctionalInterface
public interface OnNewState {
    /**
     * Call for changing state
     */
    void newState();
}
