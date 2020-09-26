package model.game.tower.towerutils;

import model.game.enemy.Enemy;

import java.util.List;


@FunctionalInterface
public interface EnemyGetter {
    List<? extends Enemy> getEnemies();
}
