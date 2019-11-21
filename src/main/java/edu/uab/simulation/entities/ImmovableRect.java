package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class ImmovableRect implements ImmovableCollision, Entity, Renderable {

    private int id;
    private int width;
    private int height;
    private PositionComponent position;
    private CollisionComponent collision;
    private PhysicsComponent physics = new PhysicsComponent(10);
    private RenderComponent render;

    public ImmovableRect(int id, int x, int y, int height, int width) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.position = new PositionComponent(x, y);
        this.collision = new CollisionComponent(this.position, this.width, this.height);
        this.render = new PlayerRenderComponent(height, width);
    }

    @Override
    public PositionComponent position() {
        return this.position;
    }

    @Override
    public PhysicsComponent physics() {
        return this.physics;
    }

    @Override
    public CollisionComponent collision() {
        return this.collision;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[Entity (ImmovableRect)]\n" +
                this.position().toString() + "\n" +
                this.physics().toString() + "\n";
    }

    @Override
    public RenderComponent render() {
        return this.render;
    }
}
