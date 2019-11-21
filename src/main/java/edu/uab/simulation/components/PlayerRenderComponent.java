package edu.uab.simulation.components;

import edu.uab.simulation.components.intrinsic.RenderComponent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerRenderComponent extends RenderComponent {

    private Node node;

    public PlayerRenderComponent(int height, int width) {
        this.node = new Rectangle(width, height, Color.BLUEVIOLET);
    }

    @Override
    public Node getNode() {
        return this.node;
    }
}
