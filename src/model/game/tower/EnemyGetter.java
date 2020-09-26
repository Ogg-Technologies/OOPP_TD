package model.game.tower;

import model.game.enemy.Enemy;

import java.util.List;


@FunctionalInterface
public interface EnemyGetter {
    List<? extends Enemy> getEnemies();
}
