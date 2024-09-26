package Tiles;

public class WaterTile implements Tile {

    @Override
    public char getCharacter() {
        return 'W';
    }

    @Override
    public String getDescription() {
        return "water";
    }
}
