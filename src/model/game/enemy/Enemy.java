package model.game.enemy;

import model.game.Health;
import utils.Vector;

/**
 * @author Oskar, Behroz, Erik
 * Super type of all Enemies
 */
public interface Enemy {
    void update();

    Vector getPos();

    void damage(int amount);

    void applyStatusEffect(StatusEffect effect);

    Health getHealth();

    void accept(EnemyVisitor visitor);
}
