package Maps;

import Tiles.BuildingTile;
import Tiles.ForestTile;
import Tiles.RoadTile;
import Tiles.Tile;

public class CityMap extends Map {
    @Override
    public Tile createTile() {
        int i = (int) Math.floor(Math.random() * 3);
        return switch (i) {
            case 0 -> new BuildingTile();
            case 1 -> new RoadTile();
            default -> new ForestTile();
        };
    }
}
