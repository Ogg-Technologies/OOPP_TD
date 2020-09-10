package model.game.tower.concretetowers;

import model.game.Game;
import model.game.enemy.DefaultEnemy;
import model.game.enemy.Enemy;
import model.game.enemy.ImmutableEnemy;
import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.tower.DefaultTower;
import model.game.tower.ImmutableTower;
import model.game.tower.TowerService;
import org.junit.jupiter.api.Test;
import utils.Vector;
import utils.VectorF;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicTowerTest {
    @Test
    void checkEnemyInRange() {
        ImmutableEnemy enemy = createMockEnemy(new VectorF(20, 1));
        DefaultTower tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(0, tower.getEnemiesInRange(3).size());

        enemy = createMockEnemy(new VectorF(0, 1));
        tower = createMockTower(enemy, new Vector(0, 1));
        assertEquals(1, tower.getEnemiesInRange(3).size());
    }


    private ImmutableEnemy createMockEnemy(VectorF pos) {
        return new ImmutableEnemy() {
            @Override
            public VectorF getPos() {
                return pos;
            }
        };
    }

    private DefaultTower createMockTower(ImmutableEnemy enemy, Vector pos) {
        return new DefaultTower(new TowerService() {
            @Override
            public List<? extends ImmutableEnemy> getEnemies() {
                ArrayList<ImmutableEnemy> enemies = new ArrayList<>();
                enemies.add(enemy);
                return enemies;
            }
        }, pos);
    }


}