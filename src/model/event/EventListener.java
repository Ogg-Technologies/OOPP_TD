package model.event;

/**
 * @author Erik
 * An interface for the Observer pattern which allows classes to listen to events that are happening in the Model.
 */
public interface EventListener {

    void onEvent(Event event);
}
