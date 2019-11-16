package edu.uab.simulation.systems;

public abstract class System<T> {

    public abstract void update(T entity, int tick);

    public void dispose(int tick) {

    }

}
