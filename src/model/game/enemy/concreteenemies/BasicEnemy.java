package model.game.enemy.concreteenemies;

import model.game.enemy.AbstractEnemy;
import model.game.enemy.EnemyService;
import model.game.enemy.EnemyVisitor;
import utils.VectorF;

public class BasicEnemy extends AbstractEnemy {

    public static final int HEALTH = 20;

    public BasicEnemy(EnemyService service, VectorF pos) {
        super(service, pos, HEALTH);
    }

    @Override
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
    }
}
