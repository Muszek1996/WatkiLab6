package sample;

public class Point {
    double x,y;
    boolean inside;

    public Point(double x, double y, boolean inside) {
        this.x = x;
        this.y = y;
        this.inside = inside;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }
}
