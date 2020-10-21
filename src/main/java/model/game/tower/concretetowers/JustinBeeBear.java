package model.game.tower.concretetowers;

import config.Config;
import model.event.Event;
import model.event.EventSender;
import model.game.enemy.Enemy;
import model.game.tower.AbstractAttackingTower;
import model.game.tower.TowerVisitor;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import model.game.tower.towerutils.chargestrategy.BurstChargeStrategy;
import utils.Vector;

/**
 * @author Behroz
 * <p>
 * Canadian bear imported. Attacks by throwing bee projectiles.
 */
public class JustinBeeBear extends AbstractAttackingTower {


    private final ProjectileCreator projectileCreator;
    private final EventSender eventSender;

    /**
     * Creates a Justin BeeBear
     *
     * @param pos               Position of bear
     * @param enemyTargeter     Gives bear access to all enemies in range
     * @param projectileCreator Lets bear create bee's
     * @param eventSender       Lets bear talk to view
     */
    public JustinBeeBear(Vector pos, EnemyTargeter enemyTargeter, ProjectileCreator projectileCreator, EventSender eventSender) {
        super(pos, Config.JustinBeeBear.RANGE, new BurstChargeStrategy(Config.JustinBeeBear.ATTACK_DELAY, Config.JustinBeeBear.ATTACKS_PER_BURST, Config.JustinBeeBear.TICKS_BETWEEN_ATTACKS), enemyTargeter);
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    @Override
    protected void attack(Enemy e) {
        int damage = (int) (Config.JustinBeeBear.BASE_DAMAGE * getActiveMultipliers().getDamageMultiplier());
        projectileCreator.addProjectile(projectileCreator.getProjectileFactory().createBee(getPos(), e, damage, Config.JustinBeeBear.BASE_HEALING, Config.JustinBeeBear.BASE_COINS_PER_BEE));
        eventSender.sendEvent(new Event(Event.Type.TOWER_FIRE, this.getClass(), getPos(), getAngle()));
    }


    @Override
    public void accept(TowerVisitor visitor) {
    }
}
