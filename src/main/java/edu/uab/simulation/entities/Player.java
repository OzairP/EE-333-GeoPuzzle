package edu.uab.simulation.entities;

import edu.uab.simulation.components.PlayerRenderComponent;
import edu.uab.simulation.components.intrinsic.*;

public class Player implements Position, Physics, Render, Input, Entity {

    private PhysicsComponent physics = new PhysicsComponent(10);
    private PositionComponent position = new PositionComponent();
    private RenderComponent render = new PlayerRenderComponent();
    private InputComponent input = new InputComponent();

    @Override
    public PhysicsComponent physics() {
        return this.physics;
    }

    @Override
    public PositionComponent position() {
        return this.position;
    }

    @Override
    public RenderComponent render() {
        return this.render;
    }

    @Override
    public InputComponent input() {
        return this.input;
    }

    @Override
    public String toString() {
        return "[Entity (Player)]\n" +
                this.position().toString() + "\n" +
                this.physics().toString() + "\n";
    }
}
