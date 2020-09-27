package model.game.projectile;

import utils.Vector;

public interface Projectile {
    Vector getPosition();

    void update();

    boolean isConsumed();
}
