package application.mapreadservice;

import model.game.map.Tile;

final class MapDecoder {

    static Tile convertToTile(char character) {
        switch (character) {
            case '.':
                return Tile.GROUND;
            case 'S':
                return Tile.START;
            case 'X':
                return Tile.BASE;
            case 'o':
                return Tile.PATH;
            default:
                throw new IllegalArgumentException("worng");
        }
    }
}
