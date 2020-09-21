package model.game.enemy;

import model.game.Health;
import utils.VectorD;

public interface Enemy {
    void update();

    VectorD getPos();

    void damage(int amount);

    void applyStatusEffect(StatusEffect effect);

    Health getHealth();

    void accept(EnemyVisitor visitor);
}
