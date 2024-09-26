import Maps.CityMap;
import Maps.WildernessMap;
import Tiles.Tile;
import Maps.Map;
import javafx.scene.image.Image;

public class GameController {
    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 10;
    private final Tile[][] grid;

    public GameController(boolean isCityMap) {
        grid = new Tile[GRID_WIDTH][GRID_HEIGHT];
        Map map = isCityMap ? new CityMap() : new WildernessMap();
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {

                grid[i][j] = map.createTile();
            }
        }
    }

    public Image getTileImage(int x, int y) {
        return grid[x][y].getImage();
    }

    public int getGridWidth() {
        return GRID_WIDTH;
    }

    public int getGridHeight() {
        return GRID_HEIGHT;
    }
}
