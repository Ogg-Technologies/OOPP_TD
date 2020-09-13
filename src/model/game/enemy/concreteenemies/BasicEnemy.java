package model.game.enemy.concreteenemies;

import model.game.Health;
import model.game.enemy.Enemy;
import model.game.enemy.EnemyService;
import model.game.enemy.EnemyVisitor;
import model.game.enemy.StatusEffect;
import utils.VectorF;

public class BasicEnemy implements Enemy {
    private final Enemy baseEnemy;

    public BasicEnemy(Enemy baseEnemy) {
        this.baseEnemy = baseEnemy;
    }

    @Override
    public EnemyService getEnemyService() {
        return baseEnemy.getEnemyService();
    }

    @Override
    public void update() {
        baseEnemy.update();
    }

    @Override
    public VectorF getPos() {
        return baseEnemy.getPos();
    }

    @Override
    public void accept(EnemyVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void damage(int amount) {
        baseEnemy.damage(amount);
    }

    @Override
    public void applyStatusEffect(StatusEffect effect) {
        baseEnemy.applyStatusEffect(effect);
    }

    @Override
    public Health getHealth() {
        return baseEnemy.getHealth();
    }
}
