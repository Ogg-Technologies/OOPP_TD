package model.event;

import utils.VectorF;

public class Event {

    private final Type type;
    private final Object cause;
    private final VectorF position;
    private final double angle;

    public Event(Type type, Object cause, VectorF position, double angle) {
        this.type = type;
        this.cause = cause;
        this.position = position;
        this.angle = angle;
    }

    public Type getType() {
        return type;
    }

    public Object getCause() {
        return cause;
    }

    public VectorF getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }

    public enum Type {
        TOWER_ATTACK,
        ENEMY_DEATH,
    }
}
