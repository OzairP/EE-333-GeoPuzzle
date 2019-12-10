package edu.uab.simulation.math;

public class TerminalVector extends Vector {
    private double terminal;

    public TerminalVector(double terminal) {
        super();
        this.terminal = terminal;
    }

    public TerminalVector(double terminal, double x, double y) {
        super(x, y);
        this.terminal = terminal;
    }

    public TerminalVector(double terminal, Vector u) {
        super(u.getX(), u.getY());
        this.terminal = terminal;
    }

    @Override
    public double getX() {
        if (super.getX() == 0) {
            return 0;
        }

        if (super.getX() < 0) {
            return Math.max(super.getX(), -1 * this.terminal);
        }

        return Math.min(super.getX(), this.terminal);
    }

    @Override
    public double getY() {
        if (super.getY() == 0) {
            return 0;
        }

        if (super.getY() < 0) {
            return Math.max(super.getY(), -1 * this.terminal);
        }

        return Math.min(super.getY(), this.terminal);
    }

    @Override
    public TerminalVector add(Vector n) {
        return new TerminalVector(this.terminal, this.getX() + n.getX(), this.getY() + n.getY());
    }

    @Override
    public TerminalVector multiply(double n) {
        return new TerminalVector(this.terminal, this.getX() * n, this.getY() * n);
    }

    @Override
    public TerminalVector multiply(double n1, double n2) {
        return new TerminalVector(this.terminal, this.getX() * n1, this.getY() * n2);
    }

    @Override
    public TerminalVector divide(double n) {
        return new TerminalVector(this.terminal, this.getX() / n, this.getY() / n);
    }

    @Override
    public Vector subtract(Vector n) {
        return new TerminalVector(this.terminal, this.getX() - n.getX(), this.getY() - n.getY());
    }
}
