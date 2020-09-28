package model.game.tower;

import model.event.EventSender;
import model.game.tower.towerutils.EnemyGetter;
import model.game.tower.towerutils.ProjectileCreator;
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

    /**
     * Creates a grizzly tower at position v
     * @param v, a {@code Vector} that describes position
     */
    public void createGrizzlyBear(Vector v) {
        towers.add(factory.createGrizzlyBear(v));
    }
}
