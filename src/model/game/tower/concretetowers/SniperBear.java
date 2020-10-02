package model.game.tower.concretetowers;


import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.tower.AbstractAttackingTower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import model.game.tower.towerutils.chargestrategy.ConstantChargeStrategy;
import utils.Vector;

/**
 * @author Behroz
 * A tower with long range and slow attack speed. Uses hitscan to damage enemies instead of creating a projectile
 */
public class SniperBear extends AbstractAttackingTower {

    private static final int RANGE = 40;
    private static final int UPDATES_BETWEEN_ATTACKS = 176;
    private static final int BASE_DAMAGE = 4;
    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public SniperBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, RANGE, new ConstantChargeStrategy(UPDATES_BETWEEN_ATTACKS), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        e.damage(BASE_DAMAGE);
        eventSender.sendEvent(new Event(Event.Type.TOWER_ATTACK, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
