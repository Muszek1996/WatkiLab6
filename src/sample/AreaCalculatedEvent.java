package sample;

import java.util.EventObject;

public class AreaCalculatedEvent extends EventObject {

    double area;

    public AreaCalculatedEvent(Object source, double area) {
        super(source);
        this.area = area;
    }

    public double getArea() {
        return area;
    }

}
