package model.game.enemy;

import model.game.enemy.concreteenemies.BasicEnemy;
import model.game.enemy.concreteenemies.BasicEnemy.Type;
import utils.VectorF;

public class EnemyFactory {
    private final EnemyService service;

    public EnemyFactory(EnemyService service) {
        this.service = service;
    }

    public Enemy createFishstick(VectorF pos) {
        return new BasicEnemy(service, pos, Type.FISHSTICK);
    }

    public Enemy createSwordfish(VectorF pos) {
        return new BasicEnemy(service, pos, Type.SWORDFISH);
    }

    public Enemy createFishAndChips(VectorF pos) {
        return new BasicEnemy(service, pos, Type.FISH_AND_CHIPS);
    }

    public Enemy createFishInABoat(VectorF pos) {
        return new BasicEnemy(service, pos, Type.FISH_IN_A_BOAT);
    }

    public Enemy createSailfish(VectorF pos) {
        return new BasicEnemy(service, pos, Type.SAILFISH);
    }

    public Enemy createShark(VectorF pos) {
        return new BasicEnemy(service, pos, Type.SHARK);
    }

    public Enemy createFishInFishTank(VectorF pos) {
        return new BasicEnemy(service, pos, Type.FISH_IN_FISH_TANK);
    }
}
