package edu.uab.simulation.components;

import edu.uab.simulation.components.intrinsic.RenderComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DoorRenderComponent extends RenderComponent {

    private Rectangle node;

    public DoorRenderComponent(int height, int width) {
        this.node = new Rectangle(width, height, Color.RED);
    }

    @Override
    public Rectangle getNode() {
        return this.node;
    }

    public void setColor(Color color) {
        this.node.setFill(color);
    }
}
