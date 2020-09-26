package model.event;

/**
 * Used for sending events from different parts of the model to all its listeners (which are stored in one place)
 */
@FunctionalInterface
public interface EventSender {

    /**
     * Sends a created event to the place where event listeners are stored
     * @param event The event to be sent
     */
    void sendEvent(Event event);
}
