package edu.uab.simulation;

import edu.uab.simulation.components.intrinsic.AcceptsInput;
import edu.uab.simulation.components.intrinsic.Collidable;
import edu.uab.simulation.components.intrinsic.HasPhysics;
import edu.uab.simulation.components.intrinsic.Renderable;
import edu.uab.simulation.entities.Entity;

import java.util.TimerTask;

public class Simulation extends TimerTask {

    public final World world;

    public Simulation(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        this.nextTick();
        System.out.println(world);
    }

    public void nextTick() {
        int tick = this.world.getTick();

        for (Entity entity : this.world.entities) {
            if (entity instanceof AcceptsInput) {
                this.world.input.update((AcceptsInput) entity, tick, this.world.entities);
            }

            if (entity instanceof HasPhysics) {
                this.world.physics.update((HasPhysics) entity, tick, this.world.entities);
            }

            if (entity instanceof Collidable) {
                this.world.collision.update((Collidable) entity, tick, this.world.entities);
            }

            if (entity instanceof Renderable) {
                this.world.render.update((Renderable) entity, tick, this.world.entities);
            }

        }

        this.world.input.dispose(this.world.entities, tick);
        this.world.physics.dispose(this.world.entities, tick);
        this.world.collision.dispose(this.world.entities, tick);
        this.world.render.dispose(this.world.entities, tick);

        this.world.incrementTick();
    }
}
