package model.game.tower.concretetowers;

import application.Constant;
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
 * Magician Bear that attacks with BombardaCharm projectiles
 */
public class BearryPotter extends AbstractAttackingTower {

    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public BearryPotter(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Constant.getInstance().BEARRY_POTTER.RANGE,
                new ConstantChargeStrategy(Constant.getInstance().BEARRY_POTTER.ATTACK_DELAY), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Constant.getInstance().BEARRY_POTTER.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        Projectile bombarda = projectileCreator.getProjectileFactory().createExplodingCharm(getPos(), e, damage);
        projectileCreator.addProjectile(bombarda);
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
