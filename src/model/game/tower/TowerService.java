package model.game.tower;

import model.game.enemy.Enemy;

import java.util.List;


public interface TowerService {
    public List<? extends Enemy> getEnemies();
}
