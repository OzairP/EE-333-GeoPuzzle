package edu.uab.simulation.entities;

import edu.uab.simulation.World;
import edu.uab.simulation.components.DoorRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class Door extends Entity implements Interactable, Renderable {

    private final PositionComponent position;
    private final PhysicsComponent physics = new PhysicsComponent(1);
    private final DoorRenderComponent render;
    private final CollisionComponent collision;

    public Door (int id, double x, double y, int size) {
        super(id);
        this.position = new PositionComponent(x, y);
        this.render = new DoorRenderComponent(size * 2, size);
        this.collision = new CollisionComponent(this.position, size, size * 2);
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
    public DoorRenderComponent render() {
        return this.render;
    }

    @Override
    public PositionComponent position() {
        return this.position;
    }

    @Override
    public void onCollision(EntityLike target, World world) {
        if (!world.isDoorUnlocked()) {
            return;
        }

        world.completeLevel();
    }

    @Override
    public String toString() {
        return "[Entity (Door)]\n" +
                this.position().toString() + "\n" +
                this.collision().toString() + "\n";
    }
}
