package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.BasicEnemy.Type;
import utils.Vector;

import java.util.List;

public class EnemyFactory {
    private final EnemyService service;
    private final List<? extends Vector> path;

    public EnemyFactory(EnemyService service, List<? extends Vector> path) {
        this.service = service;
        this.path = path;
    }

    private BasicEnemy createBasicEnemy(Type type) {
        return new BasicEnemy(service, new PathIterator(path), type);
    }

    public Enemy createFishstick() {
        return createBasicEnemy(Type.FISHSTICK);
    }

    public Enemy createSwordfish() {
        return createBasicEnemy(Type.SWORDFISH);
    }

    public Enemy createFishAndChips() {
        return createBasicEnemy(Type.FISH_AND_CHIPS);
    }

    public Enemy createFishInABoat() {
        return createBasicEnemy(Type.FISH_IN_A_BOAT);
    }

    public Enemy createSailfish() {
        return createBasicEnemy(Type.SAILFISH);
    }

    public Enemy createShark() {
        return createBasicEnemy(Type.SHARK);
    }

    public Enemy createFishInFishTank() {
        return createBasicEnemy(Type.FISH_IN_FISH_TANK);
    }
}
