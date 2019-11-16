package edu.uab.simulation;

import edu.uab.EntityList;
import edu.uab.simulation.components.intrinsic.Input;
import edu.uab.simulation.components.intrinsic.Physics;
import edu.uab.simulation.components.intrinsic.Render;
import edu.uab.simulation.entities.Entity;
import edu.uab.simulation.systems.InputSystem;
import edu.uab.simulation.systems.PhysicsSystem;
import edu.uab.simulation.systems.RenderSystem;
import javafx.scene.Node;

import java.util.List;

public class World {

    public final InputSystem input = new InputSystem();

    public final PhysicsSystem physics = new PhysicsSystem();

    public final RenderSystem render = new RenderSystem();

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
            if (entity instanceof Input) {
                this.input.update((Input) entity, tick);
            }

            if (entity instanceof Physics) {
                this.physics.update((Physics) entity, tick);
            }

            if (entity instanceof Render) {
                this.render.update((Render) entity, tick);
            }
        }

        this.input.dispose(tick);
        this.physics.dispose(tick);
        this.render.dispose(tick);

        this.incrementTick();
    }

    public int getTick() {
        return tick;
    }

    public String toString() {
        StringBuilder serial = new StringBuilder("[World]{tick=" + this.getTick() + "}\n" +
                this.input + "\n");

        for (Entity entity : this.entities) {
            serial.append(entity);
        }

        return serial.toString() + "\n";
    }

    private void incrementTick() {
        this.tick++;
    }
}
