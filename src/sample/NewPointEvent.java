package sample;

import java.util.EventObject;

public class NewPointEvent extends EventObject {
    private double x,y;
    private boolean inside;

    public NewPointEvent(Object source, double x, double y, boolean inside) {
        super(source);
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
