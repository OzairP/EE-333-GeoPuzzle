package edu.uab.simulation.components.intrinsic;

import edu.uab.simulation.math.BoundingBox;

public class CollisionComponent {

    private BoundingBox boundingBox;
    private PositionComponent position;
    private int width;
    private int height;
    private boolean isTouchingGround = false;
    private boolean ignoreCollisions = false;

    public CollisionComponent(PositionComponent position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.boundingBox = new BoundingBox(position.getX(), position.getY(), width, height);
    }

    public BoundingBox getBoundingBox() {
        if (this.position.isDirty()) {
            this.boundingBox.setPosition(this.position.getX(), this.position.getY());
        }

        return boundingBox;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isTouchingGround() {
        return isTouchingGround;
    }

    public void setTouchingGround(boolean touchingGround) {
        isTouchingGround = touchingGround;
    }

    public void setTouchingGround() {
        isTouchingGround = true;
    }

    public boolean ignoringCollisions() {
        return ignoreCollisions;
    }

    public void ignoreCollisions(boolean ignoreCollisions) {
        this.ignoreCollisions = ignoreCollisions;
    }

    public String toString() {
        return "[CollisionComponent]{boundingBox=" + this.getBoundingBox() + ";isTouchingGround=" + this.isTouchingGround() + ";isIgnoringCollisions=" + this.ignoringCollisions() + ";}";
    }
}
