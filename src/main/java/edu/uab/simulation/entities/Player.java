package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class Player extends Entity implements Collidable, Renderable, AcceptsInput, Debuggable {

    private InputComponent input = new InputComponent();
    private PhysicsComponent physics = new PhysicsComponent(10);
    private RenderComponent render;
    private PositionComponent position;
    private CollisionComponent collision;

    public Player(int id, double startX, double startY, int size) {
        super(id);
        this.position = new PositionComponent(startX, startY);
        this.collision = new CollisionComponent(this.position, size, size);
        this.render = new PlayerRenderComponent(size, size);
    }

    @Override
    public InputComponent input() {
        return this.input;
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
    public String toString() {
        return "[Entity (Player)]\n" +
                this.position().toString() + "\n" +
                this.physics().toString() + "\n";
    }
}
