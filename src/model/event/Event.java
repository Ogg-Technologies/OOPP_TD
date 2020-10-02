package model.event;

import utils.Vector;

/**
 * @author Samuel, Erik
 * An Event is something that has happened in the Model. The class responsible for the Event creates an instance of it
 * and sends it via EventSender interface to the root model class (in our case, Model) where it is sent to all
 * EventListeners to the outside. An Event always has a Type and sender (the object that caused it's class)
 */
public class Event {

    /**
     * Types of Events, used for easy filtering when the sender class is not important
     */
    public enum Type {
        UPDATE,
        TOWER_ATTACK,
        ENEMY_DEATH,
        PROJECTILE_HIT,
    }

    private final Type type;
    private final Class<?> sender;
    private final Vector position;
    private final double angle;

    /**
     * Creates an Event, which is something that has happened in the model that either something
     * in the model wants to find out has happened or potential event listeners
     * @param type The type of Event
     * @param sender The class of the object that caused the Event (send in this.getClass() as argument)
     * @param position [Optional] Some Events has a specific position
     * @param angle [Optional] Some Events has an angle TODO: Figure out if this should exist
     */
    public Event(Type type, Class<?> sender, Vector position, double angle) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        this.angle = angle;
    }

    public Event(Type type, Class<?> sender, Vector position) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        angle = 0;
    }

    public Event(Type type, Class<?> sender) {
        this.type = type;
        this.sender = sender;
        position = new Vector(0, 0);
        angle = 0;
    }

    /** @return The type of Event */
    public Type getType() {
        return type;
    }

    /** @return The class of the object that caused the event */
    public Class<?> getSender() {
        return sender;
    }

    /** @return The position of the event of zero vector if it had no position */
    public Vector getPosition() {
        return position;
    }

    /** @return The angle of the event or 0 if it had no angle */
    public double getAngle() {
        return angle;
    }
}
