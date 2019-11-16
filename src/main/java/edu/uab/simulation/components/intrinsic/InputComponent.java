package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.Vector;
import edu.uab.simulation.systems.Direction;

public class InputComponent {

    public static final Vector UP_FORCE = new Vector(0, -1);
    public static final Vector DOWN_FORCE = new Vector(0, 1);
    public static final Vector LEFT_FORCE = new Vector(-1, 0);
    public static final Vector RIGHT_FORCE = new Vector(1, 0);
    public static final Vector IDLE_FORCE = new Vector(0, 0);

    public Vector getDirectionAsVector(Direction direction) {
        switch (direction) {
            case JUMP:
                return InputComponent.UP_FORCE;
            case FALL:
                return InputComponent.DOWN_FORCE;
            case LEFT:
                return InputComponent.LEFT_FORCE;
            case RIGHT:
                return InputComponent.RIGHT_FORCE;
            case IDLE:
            default:
                return InputComponent.IDLE_FORCE;
        }
    }

}
