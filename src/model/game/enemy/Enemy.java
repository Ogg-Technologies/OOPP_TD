package model.game.enemy;

import model.game.Health;
import utils.VectorF;

public interface Enemy {
    EnemyService getEnemyService();

    void update();

    VectorF getPos();

    void damage(int amount);

    void applyStatusEffect(StatusEffect effect);

    Health getHealth();

    void accept(EnemyVisitor visitor);
}
