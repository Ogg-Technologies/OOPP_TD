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
        super(baseDamager, pathIterator, Config.FlyingFish.HEALTH, Config.FlyingFish.SPEED);
    }

    @Override
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
    }
}
