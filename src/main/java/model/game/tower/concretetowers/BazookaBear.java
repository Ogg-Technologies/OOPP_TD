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

public class BazookaBear extends AbstractAttackingTower {
    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public BazookaBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Config.INSTANCE.BAZOOKA_BEAR.RANGE,
                new ConstantChargeStrategy(Config.INSTANCE.BAZOOKA_BEAR.ATTACK_DELAY), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;

    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Config.INSTANCE.BAZOOKA_BEAR.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        Projectile rocket = projectileCreator.getProjectileFactory().createRocket(getPos(), e.getPos(), damage);
        projectileCreator.addProjectile(rocket);
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
