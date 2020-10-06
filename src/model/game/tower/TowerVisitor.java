package model.game.tower;

import model.game.tower.concretetowers.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Visitor pattern for towers
 */
public interface TowerVisitor {

    /**
     * The fallback method for all tower. This method only gets called if no more specific method signature exists
     *
     * @param tower A reference to the tower that will be visited
     */
    void visit(Tower tower);

    /**
     * The visit methods for each tower type
     * @param tower A reference to the tower that will be visited
     */
    void visit(GrizzlyBear tower);
    void visit(BearryPotter tower);
    void visit(SniperBear tower);
    void visit(SovietBear tower);
    void visit(Barbearian tower);
}
