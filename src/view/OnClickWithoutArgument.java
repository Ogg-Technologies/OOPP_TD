package view;

@FunctionalInterface
/**
 * @author Sebastian
 * Interface for creating buttons in controller, but is also used in view.
 * This interface takes care of what happens when button is clicked on
 */
public interface OnClickWithoutArgument {
    /**
     * This onClicked doesn't use any arguments.
     */
    void onClick();
}
