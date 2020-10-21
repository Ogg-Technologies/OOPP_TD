package model.game.tower;

import model.game.tower.concretetowers.*;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Visitor pattern for towers
 */
public interface TowerVisitor {

    /**
     * The visit methods for each tower type
     *
     * @param tower A reference to the tower that will be visited
     */
    void visit(GrizzlyBear tower);

    void visit(BearryPotter tower);

    void visit(SniperBear tower);

    void visit(SovietBear tower);

    void visit(Barbearian tower);

    void visit(BearGrylls tower);

    void visit(Beer tower);

    void visit(RubixCubeBear tower);

    void visit(BazookaBear tower);

    void visit(Bearon tower);
}
