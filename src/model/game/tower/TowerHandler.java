package model.game.tower;

import model.event.EventSender;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TowerHandler {

    private final TowerFactory factory;
    private final List<Tower> towers;

    public TowerHandler(TowerService towerService, EventSender eventSender) {
        factory = new TowerFactory(towerService, eventSender);
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
}
