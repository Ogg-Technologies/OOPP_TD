package application;

import model.Updatable;
import view.Drawable;

/**
 * @author Oskar, Sebastian, Erik
 * This class is for creating a loop that call update on an updatable and drawable
 */
public class ApplicationLoop implements Runnable {

    private Updatable updatable;
    private Drawable drawable;

    /**
     * @param updatable    logical class that tries to update 60 times a second
     * @param drawable     visual class that updates as often as possible
     */
    public ApplicationLoop(Updatable updatable, Drawable drawable) {
        this.updatable = updatable;
        this.drawable = drawable;
    }

    /**
     * Starts the loop on a new thread
     */
    public void start() {
        new Thread(this).start();
    }


    @Override
    public synchronized void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            // Runs 60 times each second
            while (delta >= 1) {
                this.updatable.update();
                updates++;
                delta--;
            }
            this.drawable.draw();
            frames++;

            // Runs 1 time each second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " ups, " + frames + " fps");

                updates = 0;
                frames = 0;
            }
        }
    }
}
