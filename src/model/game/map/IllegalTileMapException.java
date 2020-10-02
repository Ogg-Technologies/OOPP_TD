package model.game.map;

/**
 * @author Erik
 * Exception thrown during the creation of the TileMap if its layout is illegal
 */
public class IllegalTileMapException extends RuntimeException {

    public IllegalTileMapException(String message) {
        super(message);
    }
}
