package view.particles;

import model.OnModelUpdateObserver;
import model.particles.EmitterCreator;
import model.particles.ParticleType;
import utils.VectorF;
import view.WindowState;
import view.particles.emitterdata.RockEmitterData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ParticleHandler extends JPanel implements EmitterCreator, OnModelUpdateObserver {

    private final WindowState windowState;
    private final List<Emitter> emitters;

    public ParticleHandler(WindowState windowState) {
        this.windowState = windowState;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Emitter e : emitters) {
            e.draw(g, windowState);
        }
    }

    @Override
    public void createEmitter(ParticleType type, VectorF position) {
        emitters.add(actuallyCreateEmitter(type, position));
    }

    private Emitter actuallyCreateEmitter(ParticleType type, VectorF position) {
        switch (type) {
            case EXPLOSION:

            case SMOKE:

            case FIRE:

            case FROST:

            case POISON:

            case MONEY_GENERATED:

            case ENEMY_DEATH:

            default:
                //throw new UnsupportedOperationException("The emitter you tried to create has no implementation yet");
        }
        return new Emitter(position, new RockEmitterData());
    }
}
