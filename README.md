# WatkiLab6

Java threads lab ("wątki" = threads), built with JavaFX: a Monte Carlo estimate of the area of the Batman curve (`Equation.calc`). A background `javafx.concurrent.Task` (`DrawerTask`) generates random points in [-8, 8]², reports each one through a listener so it is drawn on a canvas (inside/outside), and drives a progress bar.

- Enter the number of points, press `start`; `stop` cancels the task early
- Result = 16² × points inside / points generated, shown in a dialog
- `NewPointEvent`/`NewPointListener`, `AreaCalculatedEvent`/`AreaCalculatedListener`: custom event classes

## Run
Requires JDK 8 with JavaFX. Open in IntelliJ IDEA (`Lab6Watki.iml`) and run `sample.Main`.
