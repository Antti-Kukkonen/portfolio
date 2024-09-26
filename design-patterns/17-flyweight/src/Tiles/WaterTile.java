package Tiles;

import javafx.scene.image.Image;

public class WaterTile implements Tile {

    private static final Image image = new Image("water.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return "water";
    }
}
