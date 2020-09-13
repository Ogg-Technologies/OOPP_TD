package model.game.enemy;

import utils.Vector;

public interface EnemyService {

    Vector getFirstTargetPosition();

    Vector getNextTargetPosition(Vector currentTargetPosition);

    void damageBase(int current);
}