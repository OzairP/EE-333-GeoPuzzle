package edu.uab.simulation;

import edu.uab.EntityList;
import edu.uab.simulation.components.intrinsic.*;
import edu.uab.simulation.entities.*;
import edu.uab.simulation.systems.CollisionSystem;
import edu.uab.simulation.systems.InputSystem;
import edu.uab.simulation.systems.PhysicsSystem;
import edu.uab.simulation.systems.RenderSystem;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

public class World {

    public final InputSystem input = new InputSystem(this);

    public final PhysicsSystem physics = new PhysicsSystem(this);

    public final RenderSystem render = new RenderSystem(this);

    public final CollisionSystem collision = new CollisionSystem(this);

    public final EntityList entities;

    private boolean doorUnlocked = true;

    private int lastEntityId = -1;

    private double startX;

    private double startY;

    private int tick = 0;

    private Player player;

    private Key key;

    private Door door;

    public World(List<Node> renderList, int height, int width, double startX, double startY, int size) {
        this.startX = startX;
        this.startY = startY;
        this.entities = new EntityList(renderList);
        this.player = new Player(this.nextEntityId(), startX, startY, size);

        this.entities.add(new ImmovableRect(this.nextEntityId(), 0, -100, 100, width));
        this.entities.add(new ImmovableRect(this.nextEntityId(), 0, 700, 100, width));
        this.entities.add(new ImmovableRect(this.nextEntityId(), -100, 0, height, 100));
        this.entities.add(new ImmovableRect(this.nextEntityId(), 1280, 0, height, 100));

        this.entities.add(this.player);
    }

    public void setKeyLocation (double x, double y, int size) {
        if (this.key != null) {
            this.entities.remove(this.key);
        }

        this.key = new Key(this.nextEntityId(), x, y, size);
        this.entities.add(this.key);
    }

    public void setExitLocation (double x, double y, int size) {
        if (this.door != null) {
            this.entities.remove(this.door);
        }

        this.door = new Door(this.nextEntityId(), x, y, size);
        this.entities.add(this.door);
    }

    public int getTick() {
        return tick;
    }

    public String toString() {
        StringBuilder serial = new StringBuilder("[World]{tick=" + this.getTick() + "}\n" +
                this.input + "\n");

        for (Entity entity : this.entities) {
            if (entity instanceof Debuggable) serial.append(entity);
        }

        return serial.toString() + "\n";
    }

    protected void incrementTick() {
        this.tick++;
    }

    public int nextEntityId () {
        return ++this.lastEntityId;
    }

    public boolean isDoorUnlocked() {
        return doorUnlocked;
    }

    public void setDoorUnlocked(boolean doorUnlocked) {
        this.doorUnlocked = doorUnlocked;
        this.key.render().getNode().setVisible(!doorUnlocked);
        this.key.collision().ignoreCollisions(doorUnlocked);
        this.door.render().setColor(doorUnlocked ? Color.GREEN : Color.RED);
    }

    public void completeLevel() {
        System.out.println("COMPLETED");
    }

    public void resetLevel() {
        this.player.position().setX(this.startX);
        this.player.position().setY(this.startY);
        this.player.physics().clearVelocity();
        this.player.physics().intrinsicForces.clearImpulse();
        this.setDoorUnlocked(false);
    }
}
