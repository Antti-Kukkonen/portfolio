package Tiles;

public class RoadTile implements Tile {

    @Override
    public char getCharacter() {
        return 'R';
    }

    @Override
    public String getDescription() {
        return "road";
    }
}
