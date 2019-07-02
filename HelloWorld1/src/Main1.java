import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;

public class Main1 extends Application{

	public static void main(String[] args) {
		
		launch(args);
	}

	public void start(Stage stage) {
		Polygon triangle = new Polygon();
		ObservableList<Double> points = triangle.getPoints();
		points.setAll(100d, 100d, 150d, 50d, 250d, 150d);
		triangle.setStroke(Color.FORESTGREEN);
		triangle.setStrokeWidth(12);
		triangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
		triangle.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
		Group root = new Group();
		root.getChildren().add(triangle);
		Scene scene = new Scene(root, 600, 500, Color.BISQUE);
        stage.setTitle("Triangle Manipulation Version 001");
        stage.setScene(scene);
        scene.setOnKeyPressed((KeyEvent key) ->{
        	System.out.println("Key hit");
        	points.set(0, 10d);
        });
        
        scene.setOnMouseDragged((MouseEvent me) -> {
            System.out.println("me " + me.getY());
            points.set(1, me.getY());
        });
        
        points.addListener(
                new ListChangeListener<Double>() {
                      public void onChanged(Change<? extends Double> c) {
                          c.next();
                          System.out.println("Change: " + c.getFrom());
                      }
                }
        );
        stage.show();
	}
}
