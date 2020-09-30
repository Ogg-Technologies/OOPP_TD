package model.game.tower.towerutils;

import model.game.enemy.Enemy;

import java.util.Collection;

/**
 * Functional interface used by EnemyTargeter to obtain the ability to iterate over enemies
 */
@FunctionalInterface
public interface EnemyGetter {
    Collection<? extends Enemy> getEnemies();
}
