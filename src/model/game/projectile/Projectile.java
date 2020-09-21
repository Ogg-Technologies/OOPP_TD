package model.game.projectile;

import utils.VectorD;

public interface Projectile {
    VectorD getPosition();

    void update();

    boolean isConsumed();
}
