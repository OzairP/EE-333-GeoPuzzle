package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.Vector;

public class PositionComponent {

    private boolean dirty = false;

    private double x = 0;

    private double y = 0;

    public PositionComponent() {
    }

    public PositionComponent(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.setDirty();
    }

    public void addX(double dx) {
        this.x += dx;
        this.setDirty();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.setDirty();
    }

    public void addY(double dy) {
        this.y += dy;
        this.setDirty();
    }

    public Vector toVector() {
        return new Vector(this.getX(), this.getY());
    }

    public void setFromVector(Vector v) {
        this.setX(v.getX());
        this.setY(v.getY());
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
