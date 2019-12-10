package edu.uab.simulation.systems;

import edu.uab.EntityList;
import edu.uab.simulation.World;
import edu.uab.simulation.components.intrinsic.HasPhysics;
import edu.uab.simulation.components.intrinsic.HasPosition;
import edu.uab.simulation.components.intrinsic.ZeroGravity;
import edu.uab.simulation.entities.Entity;
import edu.uab.simulation.math.Vector;

public class PhysicsSystem extends System<HasPhysics> {

    public static final double GRAVITY = 1;

    public PhysicsSystem(World world) {
        super(world);
    }

    @Override
    public void update(HasPhysics entity, int tick, EntityList entities) {
        this.updateEntityVelocity(entity);
        this.updateEntityPosition(entity);
    }

    private void updateEntityVelocity(HasPhysics entity) {
        Vector gravity = new Vector(0, entity instanceof ZeroGravity ? 0 : entity.physics().getMass() * PhysicsSystem.GRAVITY);
        // a = F/m, since we are processing in 1 tick delV = a * 1tick
        Vector delVelocity = entity.physics().intrinsicForces.net().add(gravity).divide(entity.physics().getMass());
        Vector newVelocity = entity.physics().getVelocity().add(delVelocity);

        if (Math.abs(newVelocity.getX()) < 1) {
            newVelocity.setX(0);
        }

        if (Math.abs(newVelocity.getY()) < 1) {
            newVelocity.setY(0);
        }

        entity.physics().setVelocity(newVelocity);
    }

    private void updateEntityPosition(HasPhysics entity) {
        Vector positionalVector = entity.position().toVector().add(entity.physics().getVelocity());
        entity.position().setFromVector(positionalVector);
    }

    @Override
    public void dispose(EntityList entities, int tick) {
        super.dispose(entities, tick);
        for (Entity entity : entities) {
            if (entity instanceof HasPosition) {
                ((HasPosition) entity).position().setDirty(false);
            }
        }
    }
}
