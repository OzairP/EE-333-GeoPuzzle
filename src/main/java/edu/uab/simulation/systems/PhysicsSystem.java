package edu.uab.simulation.systems;

import edu.uab.simulation.components.intrinsic.Physics;
import edu.uab.simulation.math.Vector;

public class PhysicsSystem extends System<Physics> {

    @Override
    public void update(Physics entity, int tick) {
        this.updateEntityVelocity(entity);
        this.updateEntityPosition(entity);
    }

    private void updateEntityVelocity(Physics entity) {
        // a = F/m, since we are processing in 1 tick delV = a * 1tick
        Vector delVelocity = entity.physics().getAppliedForce().divide(entity.physics().getMass());
        Vector newVelocity = entity.physics().getVelocity().add(delVelocity);

        entity.physics().setVelocity(newVelocity);
    }

    private void updateEntityPosition(Physics entity) {
        Vector positionalVector = entity.position().toVector().add(entity.physics().getVelocity());
        entity.position().setFromVector(positionalVector);
    }

}
