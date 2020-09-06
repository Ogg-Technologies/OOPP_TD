package model.game.enemy;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

public class EnemyHandler {
    private final EnemyFactory enemyFactory;
    private final List<Enemy> enemies;

    public EnemyHandler(EnemyService service) {
        enemyFactory = new EnemyFactory(service);
        enemies = new ArrayList<>();
    }

}
