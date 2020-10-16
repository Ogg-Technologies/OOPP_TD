package view.particles;

import model.event.Event;
import model.event.EventListener;
import model.game.projectile.concreteprojectile.BombardaCharm;
import model.game.projectile.concreteprojectile.Rock;
import model.game.tower.concretetowers.Barbearian;
import model.game.tower.concretetowers.BearGrylls;
import model.game.tower.concretetowers.SniperBear;
import model.game.tower.concretetowers.SovietBear;
import view.WindowState;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author Samuel, Erik, Oskar
 * Class used for handling everything that has to do with emitters and particles
 * Listens for specific events and creates particle emitters from that
 * Is used by swingView
 */
public final class ParticleHandler extends JPanel implements EventListener {

    // Break up into multiple maps to have different particle emitters for the same sender class
    private final Map<Class<?>, EmitterCreator> particleMap = new HashMap<>();
    private final WindowState windowState;

    private final Collection<Emitter> emitters = new ArrayList<>();

    public ParticleHandler(WindowState windowState) {
        this.windowState = windowState;

        linkEventToFactoryMethod();
    }

    private void linkEventToFactoryMethod() {
        particleMap.put(Rock.class, EmitterFactory::createRockEmitter);
        particleMap.put(BombardaCharm.class, EmitterFactory::createBombardaEmitter);
        particleMap.put(SniperBear.class, EmitterFactory::createSniperSmokeEmitter);
        particleMap.put(SovietBear.class, EmitterFactory::createBulletEmitter);
        particleMap.put(Barbearian.class, EmitterFactory::createSwingEmitter);
        particleMap.put(BearGrylls.class, EmitterFactory::createCompassEmitter);
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
            synchronized (emitters) {
                emitters.add(emitterCreator.createEmitter(event.getPosition(), event.getAngle()));
            }
        }
    }

    private void update() {
        synchronized (emitters) {
            for (Iterator<Emitter> iterator = emitters.iterator(); iterator.hasNext(); ) {
                Emitter e = iterator.next();
                e.update();

                if (!e.isAlive()) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Collection<Emitter> copyEmitters;
        synchronized (emitters) {
            copyEmitters = new ArrayList<>(emitters);
        }

        for (Emitter e : copyEmitters) {
            e.draw(g, windowState);
        }
    }
}
