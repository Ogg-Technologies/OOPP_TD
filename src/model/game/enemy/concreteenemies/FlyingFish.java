package model.game.enemy.concreteenemies;

import config.Config;
import model.game.enemy.BaseDamager;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.PathIterator;

/**
 * @author Erik
 * <p>
 * FlyingFish is a weak enemy which flies over parts of the map
 * This is done by EnemyFactory sending in a modified list of path vectors to its PathIterator
 */
public class FlyingFish extends BasicEnemy {

    public FlyingFish(BaseDamager baseDamager, PathIterator pathIterator) {
        super(baseDamager, pathIterator, Config.INSTANCE.FLYING_FISH.HEALTH, Config.INSTANCE.FLYING_FISH.SPEED);
    }

    @Override
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
    }
}
