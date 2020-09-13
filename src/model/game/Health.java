package model.game;

public interface Health {
    int getCurrent();

    int getMax();

    float getFraction();

    boolean isDead();

    void setZero();
}
