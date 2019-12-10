package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.World;
import edu.uab.simulation.entities.EntityLike;

public interface Interactable extends Collidable, ZeroGravity {

    public void onCollision (EntityLike target, World world);

}
