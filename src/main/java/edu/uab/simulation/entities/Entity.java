package edu.uab.simulation.entities;

public abstract class Entity implements EntityLike {

    private final int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
