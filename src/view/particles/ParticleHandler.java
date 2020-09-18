package view.particles;

import model.OnModelUpdateObserver;
import model.particles.EmitterCreator;
import model.particles.ParticleType;
import utils.VectorF;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ParticleHandler implements EmitterCreator, OnModelUpdateObserver {

    private final List<Emitter> emitters;

    public ParticleHandler() {
        emitters = new ArrayList<>();
    }

    @Override
    public void onUpdate() {
        for (Iterator<Emitter> iterator = emitters.iterator(); iterator.hasNext(); ) {
            Emitter e = iterator.next();
            e.update();

            if (!e.isAlive()) {
                iterator.remove();
            }
        }
    }

    public void draw() {
        for (Emitter e : emitters) {
            e.draw();
        }
    }

    @Override
    public void createEmitter(ParticleType type, VectorF position) {
        emitters.add(actuallyCreateEmitter(type, position));
    }

    private Emitter actuallyCreateEmitter(ParticleType type, VectorF position) {
        switch (type) {
            case EXPLOSION:
                //return new ExplosionEmitter(...);
            case SMOKE:

            case FIRE:

            case FROST:

            case POISON:

            case MONEY_GENERATED:

            case ENEMY_DEATH:

            default:
                throw new UnsupportedOperationException("The emitter you tried to create has no implementation yet");
        }
    }
}
