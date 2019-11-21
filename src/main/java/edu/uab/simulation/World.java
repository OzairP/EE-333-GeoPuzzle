package edu.uab.simulation;

import edu.uab.EntityList;
import edu.uab.simulation.components.intrinsic.*;
import edu.uab.simulation.entities.Entity;
import edu.uab.simulation.systems.CollisionSystem;
import edu.uab.simulation.systems.InputSystem;
import edu.uab.simulation.systems.PhysicsSystem;
import edu.uab.simulation.systems.RenderSystem;
import javafx.scene.Node;

import java.util.List;

public class World {

    public final InputSystem input = new InputSystem();

    public final PhysicsSystem physics = new PhysicsSystem();

    public final RenderSystem render = new RenderSystem();

    public final CollisionSystem collision = new CollisionSystem();

    public final EntityList entities;

    private int height;

    private int width;

    private int tick = 0;

    public World(List<Node> renderList, int height, int width) {
        this.entities = new EntityList(renderList);
        this.height = height;
        this.width = width;
    }

    public void nextTick() {
        int tick = this.getTick();

        for (Entity entity : this.entities) {
            if (entity instanceof AcceptsInput) {
                this.input.update((AcceptsInput) entity, tick, this.entities);
            }

            if (entity instanceof HasPhysics) {
                this.physics.update((HasPhysics) entity, tick, this.entities);
            }

            if (entity instanceof Collidable) {
                this.collision.update((Collidable) entity, tick, this.entities);
            }

            if (entity instanceof Renderable) {
                this.render.update((Renderable) entity, tick, this.entities);
            }

        }

        this.input.dispose(this.entities, tick);
        this.physics.dispose(this.entities, tick);
        this.collision.dispose(this.entities, tick);
        this.render.dispose(this.entities, tick);

        this.incrementTick();
    }

    public int getTick() {
        return tick;
    }

    public String toString() {
        StringBuilder serial = new StringBuilder("[World]{tick=" + this.getTick() + "}\n" +
                this.input + "\n");

        for (Entity entity : this.entities) {
            if (entity instanceof Debuggable) serial.append(entity);
        }

        return serial.toString() + "\n";
    }

    private void incrementTick() {
        this.tick++;
    }
}
