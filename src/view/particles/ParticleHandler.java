package view.particles;

import model.event.Event;
import model.event.EventListener;
import model.game.tower.concretetowers.GrizzlyBear;
import utils.VectorD;
import view.WindowState;
import view.particles.emitterdata.EmitterData;
import view.particles.emitterdata.RockEmitterData;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Class used for handling everything that has to do with emitters and particles
 * Listens for specific events and creates particle emitters from that
 */
public final class ParticleHandler extends JPanel implements EventListener {

    private final WindowState windowState;
    private final Map<Class<?>, EmitterData> particleMap;

    private final List<Emitter> emitters;

    public ParticleHandler(WindowState windowState) {   // TODO: Decide if ParticleHandler should add itself as listener or if its creator should
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
        if (event.getType() == Event.Type.UPDATE) {
            update();
        } else if (particleMap.containsKey(event.getSender())) {

            EmitterData data = particleMap.get(event.getSender());
            createEmitter(event.getPosition(), data);
        }
    }

    private void createEmitter(VectorD position, EmitterData data) {
        emitters.add(new Emitter(position, data));
    }

    private void update() {
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
