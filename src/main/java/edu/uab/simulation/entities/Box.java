package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class Box implements Collidable, HasPhysics, Renderable, Entity {

    public final int height = 50;
    public final int width = 50;
    private int id;
    private PositionComponent position;
    private CollisionComponent collision;
    private PhysicsComponent physics = new PhysicsComponent(5);
    private RenderComponent render = new PlayerRenderComponent(this.height, this.width);

    public Box(int id, int x, int y) {
        this.id = id;
        this.position = new PositionComponent(x, y);
        this.collision = new CollisionComponent(this.position, this.width, this.height);
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
    public RenderComponent render() {
        return this.render;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[Entity (Box)]\n" +
                this.position().toString() + "\n" +
                this.physics().toString() + "\n";
    }
}
