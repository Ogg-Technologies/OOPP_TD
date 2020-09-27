package model.game.enemy;

import model.game.Health;
import utils.Vector;

public interface Enemy {
    void update();

    Vector getPos();

    void damage(int amount);

    void applyStatusEffect(StatusEffect effect);

    Health getHealth();

    void accept(EnemyVisitor visitor);
}
