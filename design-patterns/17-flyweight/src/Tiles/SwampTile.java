package Tiles;

import javafx.scene.image.Image;

public class SwampTile implements Tile {

    private static final Image image = new Image("swamp.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return "swamp";
    }
}
