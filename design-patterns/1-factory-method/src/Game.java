import Maps.*;

public class Game {
    public static void main(String[] args) {
        createMap(false);
    }

    public static void createMap(boolean isCityMap) {
        Map map = isCityMap ? new CityMap() : new WildernessMap();
        map.display();
    }
}
