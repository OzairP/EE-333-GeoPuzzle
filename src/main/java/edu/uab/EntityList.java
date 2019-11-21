package edu.uab;

import edu.uab.simulation.components.intrinsic.Renderable;
import edu.uab.simulation.entities.Entity;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class EntityList extends ArrayList<Entity> {

    private List<Node> sceneList;

    public EntityList(List<Node> sceneList) {
        this.sceneList = sceneList;
    }

    @Override
    public boolean add(Entity entity) {
        if (entity instanceof Renderable) {
            this.sceneList.add(((Renderable) entity).render().getNode());
        }

        return super.add(entity);
    }

    @Override
    public Entity remove(int index) {
        this.sceneList.remove(index);
        return super.remove(index);
    }
}
