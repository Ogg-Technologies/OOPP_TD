package model.event;

import utils.VectorD;

public class Event {

    public enum Type {
        TOWER_ATTACK,
        ENEMY_DEATH,
    }

    private final Type type;
    private final Class<?> sender;
    private final VectorD position;
    private final double angle;

    public Event(Type type, Class<?> sender, VectorD position, double angle) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        this.angle = angle;
    }

    public Event(Type type, Class<?> sender, VectorD position) {
        this.type = type;
        this.sender = sender;
        this.position = position;
        angle = 0;
    }

    public Event(Type type, Class<?> sender) {
        this.type = type;
        this.sender = sender;
        position = new VectorD(0, 0);
        angle = 0;
    }

    public Type getType() {
        return type;
    }

    public Class<?> getSender() {
        return sender;
    }

    public VectorD getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }
}
