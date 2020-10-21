package model.game.tower.concretetowers;


import config.Config;
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

    private final EventSender eventSender;

    public SniperBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Config.SniperBear.RANGE,
                new ConstantChargeStrategy(Config.SniperBear.ATTACK_DELAY), enemyTargeter);
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Config.SniperBear.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        e.damage(damage);
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
