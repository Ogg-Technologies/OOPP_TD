package model.game.tower;

import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.MageBear;
import model.game.tower.concretetowers.SniperBear;

/**
 * Visitor pattern for towers
 */
public interface TowerVisitor {
    void visit(Tower tower);
    void visit(GrizzlyBear tower);

    void visit(MageBear tower);

    void visit(SniperBear tower);
}
