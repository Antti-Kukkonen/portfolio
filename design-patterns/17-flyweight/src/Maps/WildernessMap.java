package Maps;

import Tiles.ForestTile;
import Tiles.SwampTile;
import Tiles.Tile;
import Tiles.WaterTile;

public class WildernessMap extends Map {
    @Override
    public Tile createTile() {
        int i = (int) Math.floor(Math.random() * 3);
        return switch (i) {
            case 0 -> new SwampTile();
            case 1 -> new WaterTile();
            default -> new ForestTile();
        };
    }
}
