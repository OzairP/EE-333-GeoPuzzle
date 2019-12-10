package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class ImmovableRect extends Entity implements ImmovableCollision, Renderable {

    private PositionComponent position;
    private CollisionComponent collision;
    private PhysicsComponent physics = new PhysicsComponent(10);
    private RenderComponent render;

    public ImmovableRect(int id, int x, int y, int height, int width) {
        super(id);
        this.position = new PositionComponent(x, y);
        this.collision = new CollisionComponent(this.position, width, height);
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
