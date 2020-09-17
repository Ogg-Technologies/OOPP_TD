package view.particles;

import utils.VectorF;

public abstract class Emitter {

    private final VectorF position;
    private boolean alive;

    public Emitter(VectorF position) {
        this.position = position;
        alive = true;
    }

    public void update() {

    }

    public void draw() {

    }

    public boolean isAlive() {
        return alive;
    }
}
