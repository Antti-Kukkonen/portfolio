package Tiles;

public class BuildingTile implements Tile {

    @Override
    public char getCharacter() {
        return 'B';
    }

    @Override
    public String getDescription() {
        return "building";
    }
}
