package model.game.tower;

import model.game.tower.concretetowers.GrizzlyBear;

/**
 * Visitor pattern for towers
 */
public interface TowerVisitor {
    void visit(Tower tower);
    void visit(GrizzlyBear tower);
}
