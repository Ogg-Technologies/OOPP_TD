package model.game.tower;

import model.event.EventSender;
import model.game.tower.concretetowers.*;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.EnemyTargeter;
import model.game.tower.towerutils.ProjectileCreator;
import utils.Vector;

/**
 * @author Oskar, Sebastian, Behroz, Samuel, Erik
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

    public Tower createSovietBear(Vector pos) {
        return new SovietBear(pos, new EnemyTargeter(enemyGetter), eventSender);
    }

    public Tower createBarbearian(Vector pos) {
        return new Barbearian(pos, new EnemyTargeter(enemyGetter), eventSender);
    }

    /**
     * Returns a concrete tower based on the class of that tower.
     *
     * @param pos        the pos where the tower should be
     * @param towerClass the class that represent the concrete tower
     * @return the concrete tower
     */
    public Tower createTower(Vector pos, Class<? extends Tower> towerClass) {
        if (towerClass == GrizzlyBear.class) {
            return createGrizzlyBear(pos);
        }
        if (towerClass == BearryPotter.class) {
            return createMageBear(pos);
        }
        if (towerClass == SniperBear.class) {
            return createSniperBear(pos);
        }
        if (towerClass == SovietBear.class) {
            return createSovietBear(pos);
        }
        if (towerClass == Barbearian.class) {
            return createBarbearian(pos);
        }
        throw new IllegalArgumentException("Tower class: " + towerClass + " is not recognized");
    }
}
