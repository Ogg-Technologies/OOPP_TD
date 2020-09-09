package model.game.tower;

import utils.Vector;

public interface Tower extends ImmutableTower {
    TowerService getTowerService();

    Vector getPos();

    void update();
}
