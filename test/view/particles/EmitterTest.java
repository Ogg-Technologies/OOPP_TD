package view.particles;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.VectorD;
import view.particles.distribution.LinearIntegerDistribution;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import static org.junit.jupiter.api.Assertions.*;

class EmitterTest {

    @Test
    void defaultEmitterWorks() {
        Emitter e = new Emitter.Builder()
                .setEmitterPosition(new VectorD(0, 0))
                .setImage(createMockImage())
                .build();

        while (e.isAlive()) {
            e.update();
        }
    }

    @Test
    void changedLifetimeWorks() {
        Emitter e = new Emitter.Builder()
                .setEmitterPosition(new VectorD(0, 0))
                .setImage(createMockImage())
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
            builder.setImage(createMockImage());
            assertThrows(RuntimeException.class, builder::build);
        }

        @Test
        void builderThrowsExceptionWithoutImage() {
            Emitter.Builder builder = new Emitter.Builder();
            builder.setEmitterPosition(new VectorD(2, 2));
            assertThrows(RuntimeException.class, builder::build);
        }

        @Test
        void builderCreatesEmitter() {
            assertDoesNotThrow(() -> new Emitter.Builder()
                    .setEmitterPosition(new VectorD(0, 0))
                    .setImage(createMockImage())
                    .build());
        }
    }

    private Image createMockImage() {
        return new Image() {
            @Override
            public int getWidth(ImageObserver imageObserver) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver imageObserver) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String s, ImageObserver imageObserver) {
                return null;
            }
        };
    }
}