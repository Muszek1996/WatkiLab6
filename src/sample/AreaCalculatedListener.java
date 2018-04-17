package sample;

import java.util.EventListener;

public interface AreaCalculatedListener extends EventListener {
    void onAreaCalculated(AreaCalculatedEvent event);
}

