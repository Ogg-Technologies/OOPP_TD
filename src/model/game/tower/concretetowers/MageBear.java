package model.game.tower.concretetowers;

import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.projectile.Projectile;
import model.game.tower.AbstractAttackingTower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import utils.Vector;

/**
 * @author Erik
 * Magician Bear that attacks with spells or charms
 */
public class MageBear extends AbstractAttackingTower {

    private static final double RANGE = 6.5;
    private static final int UPDATES_BETWEEN_ATTACKS = 100;
    private static final int BASE_DAMAGE = 5;

    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public MageBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, RANGE, new ConstantChargeStrategy(UPDATES_BETWEEN_ATTACKS), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        Projectile bombarda = projectileCreator.getProjectileFactory().createExplodingCharm(getPos(), e, BASE_DAMAGE);
        projectileCreator.addProjectile(bombarda);
        eventSender.sendEvent(new Event(Event.Type.TOWER_ATTACK, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
