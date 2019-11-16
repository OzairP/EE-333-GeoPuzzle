package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.Vector;

public class PhysicsComponent {

    private double mass;

    private double coefficientOfFriction = 0;

    private Vector appliedForce = new Vector(0, 0);

    private Vector velocity = new Vector(0, 0);

    public PhysicsComponent(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector getAppliedForce() {
        return appliedForce;
    }

    public void setAppliedForce(Vector appliedForce) {
        this.appliedForce = appliedForce;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double getCoefficientOfFriction() {
        return coefficientOfFriction;
    }

    public void setCoefficientOfFriction(double coefficientOfFriction) {
        this.coefficientOfFriction = coefficientOfFriction;
    }

    public String toString() {
        return "[PhysicsComponent]{mass=" + this.getMass() + ";coeffOfFric= " + this.coefficientOfFriction + ";appliedForce=" + this.getAppliedForce() + ";velocity=" + this.getVelocity() + ";}";
    }
}
