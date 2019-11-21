package edu.uab.simulation.math;

public class IntrinsicForces {

    private Vector constant = new Vector();
    private Vector applied = new Vector();
    private Vector impulse = new Vector();

    public Vector net() {
        return this.constant.add(this.applied).add(this.impulse);
    }

    public void setApplied(Vector applied) {
        this.applied = applied;
    }

    public void setImpulse(Vector impulse) {
        this.impulse = impulse;
    }

    public void clearImpulse() {
        this.impulse = new Vector();
    }

    public String toString() {
        return "[IntrinsicForces]{constant=" + this.constant + "; applied=" + this.applied + "; impulse=" + this.impulse + "; net=" + this.net() + ";}";
    }

}
