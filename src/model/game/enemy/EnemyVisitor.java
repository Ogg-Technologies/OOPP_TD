package model.game.enemy;

public interface EnemyVisitor {
    /**
     * The fallback method for all enemies. This method only gets called if no more specific method signature exists
     *
     * @param enemy A reference to the enemy that will be visited
     */
    void visit(Enemy enemy);
}