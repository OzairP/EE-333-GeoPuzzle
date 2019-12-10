package edu.uab.simulation.math;

public class Vector {

    private double x = 0;

    private double y = 0;

    public Vector() {
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector add(Vector n) {
        return new Vector(this.getX() + n.getX(), this.getY() + n.getY());
    }

    public Vector subtract(Vector n) {
        return new Vector(this.getX() - n.getX(), this.getY() - n.getY());
    }

    public Vector multiply(double n) {
        return new Vector(this.getX() * n, this.getY() * n);
    }

    public Vector multiply(double n1, double n2) {
        return new Vector(this.getX() * n1, this.getY() * n2);
    }

    public Vector divide(double n) {
        return new Vector(this.getX() / n, this.getY() / n);
    }

    public String toString() {
        return "<" + this.getX() + ", " + this.getY() + ">";
    }
}
