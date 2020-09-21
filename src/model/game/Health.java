package model.game;

public interface Health {
    int getCurrent();

    int getMax();

    double getFraction();

    boolean isDead();

    void setZero();
}
