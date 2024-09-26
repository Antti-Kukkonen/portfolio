package Tiles;

import javafx.scene.image.Image;

public class BuildingTile implements Tile {

    private static final Image image = new Image("building.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return "building";
    }
}
