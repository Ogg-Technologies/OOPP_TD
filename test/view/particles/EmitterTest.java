package view.particles;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.Vector;
import view.particles.distribution.LinearIntegerDistribution;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Erik
 */
class EmitterTest {

    @Test
    void defaultEmitterWorks() {
        Emitter e = new Emitter.Builder()
                .setEmitterPosition(new Vector(0, 0))
                .setImagePath("")
                .build();

        while (e.isAlive()) {
            e.update();
        }
    }

    @Test
    void changedLifetimeWorks() {
        Emitter e = new Emitter.Builder()
                .setEmitterPosition(new Vector(0, 0))
                .setImagePath("")
                .setNewParticlesPerUpdate(LinearIntegerDistribution.fromRange(100, 100))
                .setEmitterLifetime(1)
                .setLifetimeDistribution(LinearIntegerDistribution.fromRange(0, 1))
                .build();

        assertTrue(e.isAlive());
        e.update();
        assertFalse(e.isAlive());
    }

    @Nested
    class BuilderTest {

        @Test
        void builderThrowsExceptionWithoutEmitterPosition() {
            Emitter.Builder builder = new Emitter.Builder();
            builder.setImagePath("");
            assertThrows(RuntimeException.class, builder::build);
        }

        @Test
        void builderThrowsExceptionWithoutImage() {
            Emitter.Builder builder = new Emitter.Builder();
            builder.setEmitterPosition(new Vector(2, 2));
            assertThrows(RuntimeException.class, builder::build);
        }

        @Test
        void builderCreatesEmitter() {
            assertDoesNotThrow(() -> new Emitter.Builder()
                    .setEmitterPosition(new Vector(0, 0))
                    .setImagePath("")
                    .build());
        }
    }
}