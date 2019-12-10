package edu.uab.simulation.systems;

import edu.uab.EntityList;
import edu.uab.simulation.World;
import edu.uab.simulation.components.intrinsic.Collidable;
import edu.uab.simulation.components.intrinsic.ImmovableCollision;
import edu.uab.simulation.components.intrinsic.Interactable;
import edu.uab.simulation.entities.EntityLike;
import edu.uab.simulation.math.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class CollisionSystem extends System<Collidable> {

    public static final double COEFFICIENT_OF_RESTITUTION = 0.75;

    public static final double COEFFICIENT_OF_FRICTION = 0.1;

    /**
     * A map of collisions already checked. The key is always less then the V
     */
    private HashMap<Integer, ArrayList<Integer>> cache = new HashMap<>();

    public CollisionSystem(World world) {
        super(world);
    }

    @Override
    public void update(Collidable entity, int tick, EntityList entities) {
        // This entity is immovable or interactable, so we won't collision check it
        // Only movable entities are collision checked against other collidable entities
        if (entity instanceof ImmovableCollision || entity instanceof Interactable) {
            return;
        }

        entity.collision().setTouchingGround(false);

        entity.physics().intrinsicForces.clearImpulse();
        entity.physics().intrinsicForces.clearFriction();

        for (EntityLike targetEntity : entities) {
            if (targetEntity == entity) {
                continue;
            }

            Collidable target = (Collidable) targetEntity;

            if (target.collision().ignoringCollisions()) {
                continue;
            }

            Integer key = Math.min(entity.getId(), targetEntity.getId());
            Integer value = Math.max(entity.getId(), targetEntity.getId());

            // Ensure we will always have an array list
            this.cache.putIfAbsent(key, new ArrayList<>());

            // Check cache of we have already checked for this collision
            if (this.cache.get(key).contains(value)) {
                continue;
            }

            if (!entity.collision().getBoundingBox().collidesWith(target.collision().getBoundingBox())) {
                this.cache.get(key).add(value);
                continue;
            }

            if (target instanceof Interactable) {
                ((Interactable) target).onCollision(targetEntity, this.getWorld());
                return;
            }

            entity.collision().setTouchingGround(entity.collision().isTouchingGround() || entity.position().getY() + entity.collision().getHeight() == target.position().getY());

            // First we have to move both objects right outside of each other. If they have a slow enough
            // velocity but are intersecting they won't have enough speed to exit the target

            // First move across X axis by checking the velocity of the entity so we know where it originated from
            Double delX = null;
            if (entity.physics().getVelocity().getX() != 0) {
                if (entity.physics().getVelocity().getX() >= 0) {
                    delX = -1 * entity.position().getX() - entity.collision().getWidth() + target.position().getX();
                } else {
                    delX = target.position().getX() + target.collision().getWidth() - entity.position().getX();
                }
            }

            Double delY = null;
            if (entity.physics().getVelocity().getY() != 0) {
                if (entity.physics().getVelocity().getY() >= 0) {
                    delY = -1 * entity.position().getY() - entity.collision().getHeight() + target.position().getY();
                } else {
                    delY = target.position().getY() + target.collision().getHeight() - entity.position().getY();
                }
            }

            // Touching ground, no need for collision resolution, just apply friction
            if (delX == null && delY == null || entity.collision().isTouchingGround()) {
                // F_f = F_n * mu
                double normalForce = entity.physics().getMass() * PhysicsSystem.GRAVITY;
                // Frictional force needs to be applied in the opposite direction of motion
                double directionalCoefficient = entity.physics().getVelocity().getX() > 1 ? 1 : -1;
                double frictionalForce = directionalCoefficient * normalForce * CollisionSystem.COEFFICIENT_OF_FRICTION;
                entity.physics().intrinsicForces.setFriction(new Vector(frictionalForce, 0));
                continue;
            }

            if (delX != null && delY == null) {
                if (entity.position().getY() + entity.collision().getHeight() == target.position().getY() || target.position().getY() + target.collision().getHeight() == entity.position().getY()) {
                    continue;
                }

                entity.position().addX(delX);
            } else if (delX == null && delY != null) {
                if (entity.position().getX() + entity.collision().getWidth() == target.position().getX() || target.position().getX() + target.collision().getWidth() == entity.position().getX()) {
                    continue;
                }

                entity.position().addY(delY);
            } else if (Math.abs(delX) <= Math.abs(delY)) {
                entity.position().addX(delX);
            } else {
                entity.position().addY(delY);
            }

            // ---
            boolean isHorizontalCollision = false;

            if (delX != null && delY != null && Math.abs(delX) <= Math.abs(delY)) {
                isHorizontalCollision = true;
            } else if (delX != null && delY == null) {
                isHorizontalCollision = true;
            }

            if (target instanceof ImmovableCollision) {
                // If the target is immovable, the target will absorb the velocity (KE) and return
                // it as Impulse force. The dampening coefficient represents internal friction and energy
                // loss.
                Vector velocity = entity.physics().getVelocity();
                double dampeningCoefficient = (1 - CollisionSystem.COEFFICIENT_OF_RESTITUTION);
                double impactVelocity = dampeningCoefficient * (isHorizontalCollision ? velocity.getX() : velocity.getY());
                double impulseForce = -1 * entity.physics().getMass() * impactVelocity;

                // Clear velocity on the impact axis
                entity.physics().setVelocity(velocity.multiply(isHorizontalCollision ? 0 : 1, isHorizontalCollision ? 1 : 0));
                // Set an impulse force on the impact axis. It will be cleared on the dispose so it is only applied
                // for one tick.
                entity.physics().intrinsicForces.setImpulse(new Vector(isHorizontalCollision ? impulseForce : 0, isHorizontalCollision ? 0 : impulseForce));
            } else {
                // TODO
                java.lang.System.out.println(entity + "\n" + target);
                double m0 = entity.physics().getMass();
                double m1 = target.physics().getMass();
                Vector u0 = entity.physics().getVelocity();
                Vector u1 = target.physics().getVelocity();

                double v0 = isHorizontalCollision ? u0.getX() : u0.getY();
                double v1 = isHorizontalCollision ? u1.getX() : u1.getY();

                double vf0 = (CollisionSystem.COEFFICIENT_OF_RESTITUTION * m1 * (v1 - v0) + (m0 * v0) + (m1 * v1)) / (m0 + m1);
                double vf1 = (CollisionSystem.COEFFICIENT_OF_RESTITUTION * m0 * (v0 - v1) + (m0 * v0) + (m1 * v1)) / (m0 + m1);

                if (isHorizontalCollision) {
                    u0.setX(vf0);
                    u1.setX(vf1);
                } else {
                    u0.setY(vf0);
                    u1.setY(vf1);
                }
                java.lang.System.out.println(vf0 + " " + vf1 + "\n");
            }
        }

    }

    @Override
    public void dispose(EntityList entities, int tick) {
        super.dispose(entities, tick);
        this.cache.clear();
    }
}
