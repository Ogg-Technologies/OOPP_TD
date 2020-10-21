package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.FlyingFish;

/**
 * @author Oskar, Erik, Samuel
 * An interface for the Visitor pattern for Enemies
 */
public interface EnemyVisitor {

    /**
     * The visit methods for each BasicEnemy type
     *
     * @param enemy A reference to the enemy that will be visited
     */
    void visit(BasicEnemy.Fishstick enemy);

    void visit(BasicEnemy.Swordfish enemy);

    void visit(BasicEnemy.FishAndChips enemy);

    void visit(BasicEnemy.FishInABoat enemy);

    void visit(BasicEnemy.Sailfish enemy);

    void visit(BasicEnemy.Shark enemy);

    void visit(BasicEnemy.FishInAFishTank enemy);

    void visit(BasicEnemy.TankSinatra enemy);

    void visit(FlyingFish enemy);
}
