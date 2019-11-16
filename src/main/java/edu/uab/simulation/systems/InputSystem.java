package edu.uab.simulation.systems;

import edu.uab.simulation.components.intrinsic.Input;
import edu.uab.simulation.math.Vector;

public class InputSystem extends System<Input> {

    private Direction direction = Direction.IDLE;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setIdleDirection() {
        this.setDirection(Direction.IDLE);
    }

    @Override
    public void update(Input entity, int tick) {
        Vector appliedForce = entity.input().getDirectionAsVector(this.getDirection());
        entity.physics().setAppliedForce(appliedForce);
    }

    public String toString() {
        return "[InputSystem]{direction=" + this.getDirection() + "}";
    }
}
