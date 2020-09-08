package model.game.tower;

public interface Tower {
    TowerService getTowerService();

    //TODO:
    int getXPos();

    int getYPos();

    void update();
}
