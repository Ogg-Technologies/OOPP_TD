package application;

import model.Updatable;
import view.Drawable;
import view.ShutDownAble;

public class ApplicationLoop implements Runnable {

    private Updatable updatable;
    private Drawable drawable;
    private final ShutDownAble shutDownAble;

    public ApplicationLoop(Updatable updatable, Drawable drawable, ShutDownAble shutDownAble) {
        this.updatable = updatable;
        this.drawable = drawable;
        this.shutDownAble = shutDownAble;
    }

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

        while (!shutDownAble.isShutDown()) {
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
