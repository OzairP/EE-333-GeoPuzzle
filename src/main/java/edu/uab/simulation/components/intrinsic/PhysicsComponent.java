package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.IntrinsicForces;
import edu.uab.simulation.math.TerminalVector;
import edu.uab.simulation.math.Vector;

public class PhysicsComponent {

    public static final double TERMINAL_VELOCITY = 50;

    private double mass;
    public IntrinsicForces intrinsicForces = new IntrinsicForces();
    private Vector velocity = new TerminalVector(PhysicsComponent.TERMINAL_VELOCITY);

    public PhysicsComponent(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void clearVelocity() {
        this.velocity = new TerminalVector(PhysicsComponent.TERMINAL_VELOCITY);
    }

    public String toString() {
        return "[PhysicsComponent]{mass=" + this.getMass() + ";intrinsicForces=" + this.intrinsicForces + ";velocity=" + this.getVelocity() + ";}";
    }
}
