package edu.uab.simulation.systems;

import edu.uab.EntityList;

public abstract class System<T> {

    public abstract void update(T entity, int tick, EntityList entities);

    public void dispose(EntityList entities, int tick) {

    }


}
