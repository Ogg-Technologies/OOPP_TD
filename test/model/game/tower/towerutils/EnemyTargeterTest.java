package model.game.tower.towerutils;

import model.game.Mock;
import org.junit.jupiter.api.Test;
import utils.Vector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Erik
 */
class EnemyTargeterTest {

    @Test
    void canFindTarget() {
        EnemyTargeter enemyTargeter = Mock.createMockEnemyTargeter();

        assertNull(enemyTargeter.getEnemyToAttack(new Vector(3, 4), 4.99));
        assertNotNull(enemyTargeter.getEnemyToAttack(new Vector(4, 3), 5));
    }


}