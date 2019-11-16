package edu.uab.simulation.systems;

import edu.uab.simulation.components.intrinsic.Render;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class RenderSystem extends System<Render> {

    @Override
    public void update(Render entity, int tick) {
        if (!entity.position().isDirty()) {
            return;
        }

        Node node = entity.render().getNode();

        if (node instanceof Rectangle) {
            ((Rectangle) node).setX(entity.position().getX());
            ((Rectangle) node).setY(entity.position().getY());
        }
    }
}
