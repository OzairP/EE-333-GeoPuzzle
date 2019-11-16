package edu.uab.simulation.math;

public class Vector {

    private double x = 0;

    private double y = 0;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vector add(Vector n) {
        return new Vector(this.getX() + n.getX(), this.getY() + n.getY());
    }

    public Vector multiply(double n) {
        return new Vector(this.getX() * n, this.getY() * n);
    }

    public Vector divide(double n) {
        return new Vector(this.getX() / n, this.getY() / n);
    }

    public String toString() {
        return "<" + this.getX() + ", " + this.getY() + ">";
    }
}
