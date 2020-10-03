package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.BearryPotter;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.concretetowers.SniperBear;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

/**
 * @author Oskar, Behroz, Erik
 * Factory for all towers in the game
 */
public class TowerFactory {

    private final EnemyGetter enemyGetter;
    private final ProjectileCreator projectileCreator;
    private final EventSender eventSender;

    public TowerFactory(EnemyGetter enemyGetter, ProjectileCreator projectileCreator, EventSender eventSender) {
        this.enemyGetter = enemyGetter;
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    public Tower createGrizzlyBear(Vector pos) {
        return new GrizzlyBear(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);
    }


    // TODO: Decide if this should be called something else (like Bearry Potter idk)
    public Tower createMageBear(Vector pos) {
        return new BearryPotter(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);
    }

    public Tower createSniperBear(Vector pos) {
        return new SniperBear(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);

    }
}
