package sample;


import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements AreaCalculatedListener,NewPointListener,Initializable {

    @FXML private Canvas canvas;
    @FXML private ProgressBar progressBar;
    @FXML private TextField textField;
    @FXML private Label label;
    private GraphicsContext graphicsContext;
    private DrawerTask drawerTask;
    BufferedImage bufferedImage;

    final double RANGE_MIN = -8;
    final double RANGE_MAX = 8;
    int pointsNumber;
    int pointsInside = 0;
    int counter = 0;

    @Override
    public void onAreaCalculated(AreaCalculatedEvent event) {

    }

    @Override
    public synchronized void onPointCalculated(NewPointEvent event) {
        Point point = translateCoordinates(event.getX(),event.getY(),event.isInside());
        try {
            if (event.isInside()) {
                bufferedImage.setRGB((int) point.getX(), (int) point.getY(), Color.YELLOW.getRGB());
                ++pointsInside;
            } else {
                bufferedImage.setRGB((int) point.getX(), (int) point.getY(), new Color(0, 12, 33).getRGB());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ++counter;
        if(counter % 1000 == 0)
            drawImage();
    }


    private Point translateCoordinates(double x,double y,boolean inside){
        double a = 1;
        double b = canvas.getWidth() - 1;
        double c = 1;
        double d = canvas.getHeight() - 1;
        Point point = new Point(x,y,inside);

        point.setX((int) ((b - a) * (point.getX() - RANGE_MIN) / (RANGE_MAX - RANGE_MIN) + a));
        point.setY(d-(int) ((d - c) * (point.getY() - RANGE_MIN) / (RANGE_MAX - RANGE_MIN) + c));
        return point;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private void handleRunBtnAction(){
        bufferedImage = new BufferedImage((int)canvas.getWidth(),(int)canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
        graphicsContext = canvas.getGraphicsContext2D();

        counter = 0;
        pointsInside = 0;
        try {
            pointsNumber = Integer.parseInt(textField.getText());
            if(pointsNumber<0) throw new Exception();
        }catch(Exception e){
            showErrorDialog();
            return;
        }
        drawerTask = new DrawerTask(pointsNumber);
        drawerTask.setOnSucceeded(event -> {
            drawImage();
            countResult();
        });
        drawerTask.addNewPointListener(this);
        new Thread(drawerTask).start();
        progressBar.progressProperty().bind(drawerTask.progressProperty());
    }

    @FXML
    private void handleStopBtnAction(){
        drawerTask.cancel();
        countResult();
    }

    private void drawImage() {
        graphicsContext.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
    }

    private void countResult() {
        double val = 16.0*16.0*(double)pointsInside/pointsNumber/drawerTask.getProgress();

        label.setText(String.valueOf(val));
        showResultDialog(val);
    }

    public void showResultDialog(double result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        //alert.initOwner();
        alert.setTitle("Result");
        alert.setHeaderText(null);
        alert.setContentText("Integral result is:\n" + result);
        alert.showAndWait();
    }

    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Invalid data");
        alert.setHeaderText(null);
        alert.setContentText("Wprowadz poprawną wartosc dodatnią");
        alert.showAndWait();
    }

}
