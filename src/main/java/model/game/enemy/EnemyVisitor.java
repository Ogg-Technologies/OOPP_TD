package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;

/**
 * @author Oskar, Erik
 * An interface for the Visitor pattern for Enemies
 */
public interface EnemyVisitor {
    /**
     * The fallback method for all enemies. This method only gets called if no more specific method signature exists
     *
     * @param enemy A reference to the enemy that will be visited
     */
    void visit(Enemy enemy);

    /**
     * The visit methods for each BasicEnemy type
     * @param enemy A reference to the enemy that will be visited
     */
    void visit(BasicEnemy.Fishstick enemy);
    void visit(BasicEnemy.Swordfish enemy);
    void visit(BasicEnemy.FishAndChips enemy);
    void visit(BasicEnemy.FishInABoat enemy);
    void visit(BasicEnemy.Sailfish enemy);
    void visit(BasicEnemy.Shark enemy);

    void visit(BasicEnemy.FishInAFishTank enemy);

    void visit(FlyingFish enemy);
}
