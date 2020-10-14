package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.*;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import model.game.tower.towerutils.TowerFinder;
import utils.Vector;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
 * Factory for all towers in the game
 */
public class TowerFactory {

    private final EnemyGetter enemyGetter;
    private final TowerFinder towerFinder;
    private final ProjectileCreator projectileCreator;
    private final EventSender eventSender;

    public TowerFactory(EnemyGetter enemyGetter, TowerFinder towerFinder, ProjectileCreator projectileCreator, EventSender eventSender) {
        this.enemyGetter = enemyGetter;
        this.towerFinder = towerFinder;
        this.projectileCreator = projectileCreator;
        this.eventSender = eventSender;
    }

    public Tower createBearGrylls(Vector pos) {
        return new BearGrylls(pos, towerFinder, eventSender);
    }

    public Tower createGrizzlyBear(Vector pos) {
        return new GrizzlyBear(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);
    }

    public Tower createBearryPotter(Vector pos) {
        return new BearryPotter(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);
    }

    public Tower createSniperBear(Vector pos) {
        return new SniperBear(pos, new EnemyTargeter(enemyGetter), projectileCreator, eventSender);
    }

    public Tower createSovietBear(Vector pos) {
        return new SovietBear(pos, new EnemyTargeter(enemyGetter), eventSender);
    }

    public Tower createBarbearian(Vector pos) {
        return new Barbearian(pos, new EnemyTargeter(enemyGetter), eventSender);
    }
}
