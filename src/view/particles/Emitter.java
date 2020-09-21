package view.particles;

import utils.VectorD;
import view.WindowState;
import view.particles.emitterdata.EmitterData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Emitter {

    private final VectorD position;
    private EmitterData data;

    private int emitterLifetime;
    private final double particlesPerUpdate;
    private final List<Particle> particles;

    public Emitter(VectorD position, EmitterData emitterData) {
        this.position = position;
        this.data = emitterData;

        emitterLifetime = data.getEmitterLifetime();
        particlesPerUpdate = data.newParticlesPerUpdate();
        particles = new ArrayList<>();
    }

    public void update() {
        if (emitterLifetime > 0) {
            for (int i = 0; i < data.newParticlesPerUpdate(); i++) {
                createNewParticle();
            }
            emitterLifetime--;
        }
        for (Iterator<Particle> iterator = particles.iterator(); iterator.hasNext(); ) {
            Particle p = iterator.next();
            p.update();
            if (p.isDead()) {
                iterator.remove();
            }
        }
    }

    private void createNewParticle() {
        particles.add(new Particle(
                data.getNewParticleLifetime(),
                position.plus(data.getNewStartPosition()),
                data.getNewStartVelocity(),
                data.getNewTileSize(),
                data.getNewFriction(),
                data.getImage()
        ));
    }

    public void draw(Graphics graphics, WindowState windowState) {
        for (Particle p : particles) {
            p.draw(graphics, windowState);
        }
    }

    public boolean isAlive() {
        return emitterLifetime > 0 || !particles.isEmpty();
    }
}
