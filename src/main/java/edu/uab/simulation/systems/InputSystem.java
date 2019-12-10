package edu.uab.simulation.systems;

import edu.uab.EntityList;
import edu.uab.simulation.World;
import edu.uab.simulation.components.intrinsic.AcceptsInput;
import edu.uab.simulation.components.intrinsic.Collidable;
import edu.uab.simulation.math.Vector;

public class InputSystem extends System<AcceptsInput> {

    private Direction previousDirection = Direction.IDLE;

    private Direction direction = Direction.IDLE;

    public InputSystem(World world) {
        super(world);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.previousDirection = this.direction;
        this.direction = direction;
    }

    public void setIdleDirection() {
        this.setDirection(Direction.IDLE);
    }

    @Override
    public void update(AcceptsInput entity, int tick, EntityList entities) {
        if (this.getDirection() == Direction.JUMP && (this.previousDirection == Direction.JUMP || (entity instanceof Collidable && !((Collidable) entity).collision().isTouchingGround()))) {
            entity.physics().intrinsicForces.setApplied(new Vector());
            return;
        }

        Vector appliedForce = entity.input().getDirectionAsVector(this.getDirection());
        entity.physics().intrinsicForces.setApplied(appliedForce);
    }

    public String toString() {
        return "[InputSystem]{direction=" + this.getDirection() + "}";
    }
}
