package view.particles;

import model.OnModelUpdateObserver;
import model.event.Event;
import model.event.EventListener;
import model.game.tower.concretetowers.GrizzlyBear;
import utils.VectorF;
import view.WindowState;
import view.particles.emitterdata.EmitterData;
import view.particles.emitterdata.RockEmitterData;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public final class ParticleHandler extends JPanel implements OnModelUpdateObserver, EventListener {

    private final WindowState windowState;
    private final Map<Class<?>, EmitterData> particleMap;

    private final List<Emitter> emitters;

    public ParticleHandler(WindowState windowState) {
        this.windowState = windowState;
        particleMap = new HashMap<>();
        emitters = new ArrayList<>();

        linkEventCauseToParticleType();
    }

    private void linkEventCauseToParticleType() {
        particleMap.put(GrizzlyBear.class, new RockEmitterData());
    }

    /**
     * Creates a particle effect if the particular event is tied to an emitter data
     * @param event Event sent from the model
     */
    @Override
    public void onEvent(Event event) {
        EmitterData data = particleMap.get(event.getSender().getClass());
        if (data == null) {
            return;
        }
        createEmitter(event.getPosition(), data);
    }

    private void createEmitter(VectorF position, EmitterData data) {
        emitters.add(new Emitter(position, data));
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
}
