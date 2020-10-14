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
 * @author Oskar, Behroz, Erik
 * Weak early-game tower that throws rocks
 */
public class GrizzlyBear extends AbstractAttackingTower {

    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public GrizzlyBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Constant.getInstance().GRIZZLY_BEAR.RANGE,
                new ConstantChargeStrategy(Constant.getInstance().GRIZZLY_BEAR.ATTACK_DELAY), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Constant.getInstance().GRIZZLY_BEAR.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        Projectile rock = projectileCreator.getProjectileFactory().createRock(getPos(), e.getPos(), damage);
        projectileCreator.addProjectile(rock);
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
