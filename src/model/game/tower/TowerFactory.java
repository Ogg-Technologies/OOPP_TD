package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.GrizzlyBear;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

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
}
