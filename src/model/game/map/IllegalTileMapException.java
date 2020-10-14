package model.game.map;

/**
 * @author Erik
 * Exception thrown during the creation of TileMap objects if its layout is illegal
 */
public class IllegalTileMapException extends Exception {

    public IllegalTileMapException(String message) {
        super(message);
    }
}
