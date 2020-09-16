package model.game.projectile;

import utils.VectorF;

public interface Projectile {
    VectorF getPosition();

    void update();

    boolean isConsumed();
}
