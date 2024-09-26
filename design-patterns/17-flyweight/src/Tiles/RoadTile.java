package Tiles;

import javafx.scene.image.Image;

public class RoadTile implements Tile {

    private static final Image image = new Image("road.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return "road";
    }
}
