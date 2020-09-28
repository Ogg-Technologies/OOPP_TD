package model.game.tower.towerutils;

import model.game.enemy.Enemy;

import java.util.Collection;


@FunctionalInterface
public interface EnemyGetter {
    Collection<? extends Enemy> getEnemies();
}
