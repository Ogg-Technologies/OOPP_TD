package view.particles;

import org.junit.jupiter.api.Test;
import utils.VectorD;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParticleTest {

    @Test
    void isDeadWithEnoughUpdates() {
        Particle particle = new Particle(100, new VectorD(0, 0), new VectorD(0, 0), 0, 0, 0, null);

        for (int i = 0; i < 100 - 1; i++) {
            particle.update();
            assertFalse(particle.isDead());
        }
        particle.update();
        assertTrue(particle.isDead());
    }
}