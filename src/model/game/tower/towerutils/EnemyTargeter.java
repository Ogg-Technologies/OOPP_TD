package model.game.tower.towerutils;

import model.game.enemy.Enemy;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class EnemyTargeter {
    private final static Random random = new Random();
    private final EnemyGetter enemyGetter;

    public EnemyTargeter(EnemyGetter enemyGetter) {
        this.enemyGetter = enemyGetter;
    }

    public Enemy getEnemyToAttack(Vector pos, double range) {
        List<? extends Enemy> enemiesInRange = getEnemiesInRange(pos, range);
        if (!enemiesInRange.isEmpty()) {
            int currentIndex = random.nextInt(enemiesInRange.size());
            return enemiesInRange.get(currentIndex);
        }
        return null;
    }

    private List<? extends Enemy> getEnemiesInRange(Vector pos, double range) {
        Collection<? extends Enemy> allEnemies = enemyGetter.getEnemies();
        List<Enemy> enemiesInRange = new ArrayList<>(); // Must be List<E> and not Collection<E> because get() call
        for (Enemy currentEnemy : allEnemies) {
            if (pos.minus(currentEnemy.getPos()).getDist() <= range) {
                enemiesInRange.add(currentEnemy);
            }
        }
        return enemiesInRange;
    }
}
