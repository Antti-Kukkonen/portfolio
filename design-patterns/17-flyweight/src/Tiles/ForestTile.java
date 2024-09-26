package Tiles;

import javafx.scene.image.Image;

public class ForestTile implements Tile {
    private static final Image image = new Image("forest.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return "forest";
    }
}
