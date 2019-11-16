package edu.uab.simulation.components;

import edu.uab.simulation.components.intrinsic.RenderComponent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerRenderComponent extends RenderComponent {

    private Node node = new Rectangle(100, 100, Color.BLUEVIOLET);

    @Override
    public Node getNode() {
        return this.node;
    }
}
