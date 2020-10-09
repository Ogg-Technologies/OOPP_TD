package model.game.tower.towerutils.buff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Oskar
 * A class which manages buffs for a Tower and calculates the currently active TowerMultipliers to it
 */
public class BuffManager {
    private final List<Buff> buffs = new ArrayList<>();
    private TowerMultipliers towerMultipliers = new TowerMultipliers();

    public void update() {
        towerMultipliers = new TowerMultipliers();
        for (Iterator<Buff> iterator = buffs.iterator(); iterator.hasNext(); ) {
            Buff buff = iterator.next();
            if (buff.updatesLeft <= 0) {
                iterator.remove();
            }
            buff.updatesLeft--;
            buff.towerModifier.modifyTowerMultipliers(towerMultipliers);
        }
    }

    /**
     * Adds a new buff to the BuffManager for the given duration
     *
     * @param towerModifier The way the buff modifies the tower
     * @param duration      The duration for the buff
     */
    public void applyBuff(TowerModifier towerModifier, int duration) {
        buffs.add(new Buff(towerModifier, duration));
    }

    /**
     * Gets the currently active modifiers for the tower
     *
     * @return The active modifiers
     */
    public TowerMultipliers getTowerMultipliers() {
        return towerMultipliers;
    }

    private class Buff {
        final TowerModifier towerModifier;
        int updatesLeft;

        public Buff(TowerModifier towerModifier, int duration) {
            this.towerModifier = towerModifier;
            updatesLeft = duration;
        }
    }
}
