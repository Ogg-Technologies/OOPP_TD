package model.game.tower;

import model.event.EventSender;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TowerHandler {

    private final TowerFactory factory;
    private final List<Tower> towers;

    public TowerHandler(EnemyGetter enemyGetter, ProjectileCreator projectileCreator, EventSender eventSender) {
        factory = new TowerFactory(enemyGetter, projectileCreator, eventSender);
        towers = new ArrayList<>();
        /*towers.add(factory.createBasicTower(new Vector(0, 1)));
        towers.add(factory.createBasicTower(new Vector(1, 0)));
        towers.add(factory.createBasicTower(new Vector(2, 0)));*/
        towers.add(factory.createGrizzlyBear(new Vector(0, 0)));
        towers.add(factory.createGrizzlyBear(new Vector(1, 0)));
        towers.add(factory.createGrizzlyBear(new Vector(2, 0)));


    }

    public List<? extends Tower> getTowers() {
        return Collections.unmodifiableList(towers);
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
        }
    }

    /**
     * Checks if there is a tower on a particular tilePos
     * @param tilePos the pos to look at
     * @return true if there is a tower on this pos, else false
     */
    public boolean isTowerAt(Vector tilePos) {
        for(Tower t : towers){
            if(t.getPos().equals(tilePos)){
                return true;
            }
        }
        return false;
    }
}
