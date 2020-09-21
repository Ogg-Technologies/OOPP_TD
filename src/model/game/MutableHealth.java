package model.game;

public class MutableHealth implements Health {
    private final int max;
    private int current;

    public MutableHealth(int max) {
        this.max = max;
        current = max;
    }

    public void damage(int amount) {
        current = Math.max(0, current - amount);
    }

    public void heal(int amount) {
        current = Math.min(max, current + amount);
    }

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public double getFraction() {
        return ((double) current) / max;
    }

    @Override
    public boolean isDead() {
        return current == 0;
    }

    @Override
    public void setZero() {
        current = 0;
    }

    @Override
    public String toString() {
        return current + "/" + max;
    }
}
