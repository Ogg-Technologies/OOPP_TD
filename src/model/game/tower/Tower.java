package model.game.tower;

import utils.Vector;

public interface Tower {
    Vector getPos();

    void update();

    double getRange();

    void accept(TowerVisitor visitor);
}
