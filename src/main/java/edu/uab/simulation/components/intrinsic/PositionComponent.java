package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.Vector;

public class PositionComponent {

    private boolean dirty = false;

    private int x = 0;

    private int y = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setDirty();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setDirty();
    }

    public Vector toVector() {
        return new Vector(this.getX(), this.getY());
    }

    public void setFromVector(Vector v) {
        this.setX((int) v.getX());
        this.setY((int) v.getY());
        this.setDirty();
    }

    public String toString() {
        return "[PositionComponent]{" + this.toVector().toString() + "}";
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public void setDirty() {
        this.dirty = true;
    }
}
