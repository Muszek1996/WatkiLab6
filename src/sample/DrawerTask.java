package sample;

import javafx.concurrent.Task;

import java.util.Random;

public class DrawerTask extends Task {


    private Point point;
    private int pointsAmount = 100000;
    private final double MIN = -8;
    private final double MAX = 8;
    private NewPointListener listener;
    private Random random;
    private NewPointEvent event;

    @Override
    protected Object call() throws Exception {
            for(int i=0; i<=pointsAmount; i++){
                generatePoint();
                updateProgress(i, pointsAmount);
            if(isCancelled()) break;
        }
        return null;
    }

    public DrawerTask(int pointsAmount) {
        this.pointsAmount = pointsAmount;
        random = new Random();
        point = new Point(0,0,Equation.calc(0,0));
        event = new NewPointEvent(this,point.getX(),point.getY(),true);

    }

    private void generatePoint() {
        event.setX((MIN + (MAX - MIN) * random.nextDouble()));
        event.setY((MIN + (MAX - MIN) * random.nextDouble()));
        if(Equation.calc(event.getX(), event.getY())) {
            event.setInside(true);
            listener.onPointCalculated(event);
        } else {
            event.setInside(false);
            listener.onPointCalculated(event);
        }
    }

    public void addNewPointListener(NewPointListener listener) {
        this.listener = listener;
    }

}
