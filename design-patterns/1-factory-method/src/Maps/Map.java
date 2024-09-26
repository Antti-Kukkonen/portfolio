package Maps;

import Tiles.Tile;

public abstract class Map {
    abstract Tile createTile();

    public void display() {
        char[][] mapMatrix = new char[3][10];

        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix[i].length; j++) {
                Tile tile = createTile();
                mapMatrix[i][j] = tile.getCharacter();
            }
        }

        for (char[] matrix : mapMatrix) {
            for (char c : matrix) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
