package edu.uab.simulation.math;

public class BoundingBox {

    private double x;
    private double y;
    private double width;
    private double height;

    public BoundingBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(BoundingBox box) {
        return box.x <= this.x + this.width &&
                box.x + box.width >= this.x &&
                box.y <= this.y + this.height &&
                box.y + box.height >= this.y;
    }

    public Side collisionSide(BoundingBox box) {
        double thisMinY = this.y;
        double thisMaxY = this.y + this.height;
        double thisMinX = this.x;
        double thisMaxX = this.x + this.width;
        double boxMinY = box.y;
        double boxMaxY = box.y + box.height;
        double boxMinX = box.x + Math.ceil(box.width / 2f);
        double boxMaxX = box.x + Math.floor(box.width / 2f);

        if (thisMaxY <= boxMinY) {
            return Side.TOP;
        } else if (thisMinY >= boxMaxY) {
            return Side.BOTTOM;
        } else if (thisMaxX <= boxMinX) {
            return Side.RIGHT;
        } else if (thisMinX >= boxMaxX) {
            return Side.LEFT;
        }

        return Side.TOP;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "[BoundingBox]{x=" + this.x + ";y=" + this.y + ";width=" + this.width + ";height=" + this.height + ";}";
    }
}
