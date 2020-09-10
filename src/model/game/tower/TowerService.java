package model.game.tower;

import java.util.List;

import model.game.enemy.ImmutableEnemy;

public interface TowerService {
    public List<? extends ImmutableEnemy> getEnemies();
}
