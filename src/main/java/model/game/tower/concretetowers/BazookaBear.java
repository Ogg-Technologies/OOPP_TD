package model.game.tower.concretetowers;


import config.Config;
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
 * @author Behroz
 * A tower which shoots rocket projectiles at enemies.
 */
public class BazookaBear extends AbstractAttackingTower {
    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public BazookaBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Config.BazookaBear.RANGE,
                new ConstantChargeStrategy(Config.BazookaBear.ATTACK_DELAY), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;

    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Config.BazookaBear.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        Projectile rocket = projectileCreator.getProjectileFactory().createRocket(getPos(), e.getPos(), damage);
        projectileCreator.addProjectile(rocket);
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
