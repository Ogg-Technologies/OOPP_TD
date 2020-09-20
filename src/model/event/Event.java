package model.event;

import utils.VectorF;

public class Event {

    public enum Type {
        TOWER_ATTACK,
        ENEMY_DEATH,
    }

    private final Type type;
    private final Object sender;
    private final VectorF position;
    private final double angle;

    public Event(Type type, Object sender, VectorF position, double angle) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        this.angle = angle;
    }

    public Event(Type type, Object sender, VectorF position) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        angle = 0;
    }

    public Event(Type type, Object sender) {
        this.type = type;
        this.sender = sender;
        position = new VectorF(0, 0);
        angle = 0;
    }

    public Type getType() {
        return type;
    }

    public Object getSender() {
        return sender;
    }

    public VectorF getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }
}
