package edu.uab.simulation.entities;

import edu.uab.simulation.World;
import edu.uab.simulation.components.ResourceRenderComponent;
import edu.uab.simulation.components.intrinsic.*;
import javafx.scene.image.ImageView;

public class Spikes extends Entity implements Interactable, Renderable {
    private final PositionComponent position;
    private final PhysicsComponent physics = new PhysicsComponent(1);
    private final ResourceRenderComponent render = new ResourceRenderComponent("fourspikesSteel.png");
    private final CollisionComponent collision;

    public Spikes (int id, double x, double y, int size) {
        super(id);
        this.position = new PositionComponent(x, y);
        ImageView node = this.render.getNode();
        node.setFitWidth(size);
        this.collision = new CollisionComponent(this.position, (int) node.getBoundsInParent().getWidth(), (int) node.getBoundsInParent().getHeight());
    }

    @Override
    public CollisionComponent collision() {
        return this.collision;
    }

    @Override
    public PhysicsComponent physics() {
        return this.physics;
    }

    @Override
    public RenderComponent render() {
        return this.render;
    }

    @Override
    public PositionComponent position() {
        return this.position;
    }

    @Override
    public void onCollision(EntityLike target, World world) {
        world.resetLevel();
    }

    @Override
    public String toString() {
        return "[Entity (Spikes)]\n" +
                this.position().toString() + "\n" +
                this.collision().toString() + "\n";
    }
}
