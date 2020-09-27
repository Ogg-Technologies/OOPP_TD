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

public class GrizzlyBear extends AbstractAttackingTower {

    private static final int RANGE = 5;
    private static final int UPDATES_BETWEEN_ATTACKS = 88;
    private static final int BASE_DAMAGE = 1;
    private ProjectileCreator projectileCreator;
    private EventSender eventSender;

    public GrizzlyBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, RANGE, new ConstantChargeStrategy(UPDATES_BETWEEN_ATTACKS), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        Projectile rock = projectileCreator.getProjectileFactory().createRock(getPos(), e.getPos(), BASE_DAMAGE);
        projectileCreator.addProjectile(rock);
        eventSender.sendEvent(new Event(Event.Type.TOWER_ATTACK, this.getClass(), getPos(), getAngle()));
    }

    @Override
    public void accept(TowerVisitor visitor) {
        visitor.visit(this);
    }
}
