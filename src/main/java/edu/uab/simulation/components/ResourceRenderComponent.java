package edu.uab.simulation.components;

import edu.uab.App;
import edu.uab.simulation.components.intrinsic.RenderComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ResourceRenderComponent extends RenderComponent {

    private ImageView node;

    public ResourceRenderComponent(String filename) {
        File file = new File("src/main/resources/images/" + filename);
        Image image = new Image(file.toURI().toString());
        this.node = new ImageView(image);
        this.node.setPreserveRatio(true);
        this.node.setSmooth(true);
        this.node.setCache(true);
    }

    @Override
    public ImageView getNode() {
        return this.node;
    }
}
