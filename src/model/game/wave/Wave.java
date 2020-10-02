package model.game.wave;

import model.game.enemy.Enemy;

import java.util.Collection;
import java.util.Iterator;

public interface Wave extends Iterator<Collection<Enemy>> {
    int getRemainingHealth();
}
