package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class Player implements Collidable, Renderable, AcceptsInput, Entity, Debuggable {

    public final int height = 50;
    public final int width = 50;
    private int id;
    private InputComponent input = new InputComponent();
    private PositionComponent position = new PositionComponent(5, 5);
    private CollisionComponent collision = new CollisionComponent(this.position, this.width, this.height);
    private PhysicsComponent physics = new PhysicsComponent(10);
    private RenderComponent render = new PlayerRenderComponent(this.height, this.width);

    public Player(int id) {
        this.id = id;
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
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[Entity (Player)]\n" +
                this.position().toString() + "\n" +
                this.physics().toString() + "\n";
    }
}
