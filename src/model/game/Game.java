package model.game;

import model.game.map.Map;

public class Game {
    private final Map map = new Map();

    public Game() {
        System.out.println(map.getTile(1, 1));
    }
}
