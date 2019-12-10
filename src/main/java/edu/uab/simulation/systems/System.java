package edu.uab.simulation.systems;

import edu.uab.EntityList;
import edu.uab.simulation.World;

public abstract class System<T> {

    private World world;

    public System(World world) {
        this.world = world;
    }

    public abstract void update(T entity, int tick, EntityList entities);

    public void dispose(EntityList entities, int tick) {

    }

    public World getWorld() {
        return world;
    }
}
