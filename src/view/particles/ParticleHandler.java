package view.particles;

import model.event.Event;
import model.event.EventListener;
import model.game.projectile.concreteprojectile.Rock;
import view.WindowState;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Class used for handling everything that has to do with emitters and particles
 * Listens for specific events and creates particle emitters from that
 */
public final class ParticleHandler extends JPanel implements EventListener {

    // Break up into multiple maps to have different particle emitters for the same sender class
    private final Map<Class<?>, EmitterCreator> particleMap;
    private final WindowState windowState;

    private final Collection<Emitter> emitters;

    public ParticleHandler(WindowState windowState) {   // TODO: Decide if ParticleHandler should add itself as listener or if its creator should
        this.windowState = windowState;
        particleMap = new HashMap<>();
        emitters = new ArrayList<>();

        linkEventToFactoryMethod();
    }

    private void linkEventToFactoryMethod() {
        particleMap.put(Rock.class, EmitterFactory::createRockEmitter);
    }

    /**
     * Updates the particles if the model sent an update event.
     * Creates a particle effect if the particular event is tied to an emitter data
     * @param event Event sent from the model
     */
    @Override
    public void onEvent(Event event) {
        if (event.getType() == Event.Type.UPDATE) {
            update();
        } else if (particleMap.containsKey(event.getSender())) {
            EmitterCreator emitterCreator = particleMap.get(event.getSender());
            emitters.add(emitterCreator.createEmitter(event.getPosition()));
        }
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

        Collection<Emitter> copyEmitters = new ArrayList<>(emitters);

        for (Emitter e : copyEmitters) {
            e.draw(g, windowState);
        }
    }
}
