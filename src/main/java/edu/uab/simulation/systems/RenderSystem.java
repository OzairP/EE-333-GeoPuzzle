package edu.uab.simulation.systems;

import edu.uab.EntityList;
import edu.uab.simulation.World;
import edu.uab.simulation.components.intrinsic.Renderable;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class RenderSystem extends System<Renderable> {

    public RenderSystem(World world) {
        super(world);
    }

    @Override
    public void update(Renderable entity, int tick, EntityList entities) {
        if (!entity.position().isDirty()) {
            return;
        }

        Node node = entity.render().getNode();

        if (node instanceof Rectangle) {
            Platform.runLater(() -> {
                ((Rectangle) node).setX(entity.position().getX());
                ((Rectangle) node).setY(entity.position().getY());
            });
        }

        if (node instanceof ImageView) {
            Platform.runLater(() -> {
                ((ImageView) node).setX(entity.position().getX());
                ((ImageView) node).setY(entity.position().getY());
            });
        }
    }
}
