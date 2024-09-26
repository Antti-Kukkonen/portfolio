package Tiles;

public class SwampTile implements Tile {

    @Override
    public char getCharacter() {
        return 'S';
    }

    @Override
    public String getDescription() {
        return "swamp";
    }
}
