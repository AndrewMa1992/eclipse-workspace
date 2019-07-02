import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Main3 extends Application {

	class Anchor extends Circle {
		ObservableList<Double> points;
		int list_index;
		double offset_x, offset_y;

		public Anchor(ObservableList<Double> points, int list_index) {
			super(points.get(list_index), points.get(list_index + 1), 10d);
			setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
			setStroke(Color.GOLD);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			this.points = points;
			this.list_index = list_index;

			setOnMousePressed((MouseEvent mouseEvent) -> {
				// record a delta distance for the drag and drop operation.
				offset_x = getCenterX() - mouseEvent.getX();
				offset_y = getCenterY() - mouseEvent.getY();
			});

			setOnMouseDragged((MouseEvent mouseEvent) -> {
				double newX = mouseEvent.getX() + offset_x;
				if (newX > 0 && newX < getScene().getWidth()) {
					setCenterX(newX);
					points.set(list_index, newX);
				}
				double newY = mouseEvent.getY() + offset_y;
				if (newY > 0 && newY < getScene().getHeight()) {
					setCenterY(newY);
					points.set(list_index + 1, newY);
				}
			});
		}
	}

	public class EditableShape {
		protected Anchor[] anchors;

        protected void configure(Group root, Shape shape) {
            // Put shape and anchors into the JavaFX display group
            root.getChildren().add(shape);
            root.getChildren().addAll(anchors);

            // Set appearance for the shape
            shape.setStrokeWidth(4);
            shape.setStrokeLineCap(StrokeLineCap.ROUND);
            shape.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
            shape.setStroke(Color.FORESTGREEN);
        }
	}

	class Triangle extends EditableShape {
		 private Polygon triangle;

	        public Triangle(Group root, Double... p) {
	            triangle = new Polygon();
	            ObservableList<Double> points = triangle.getPoints();
	            points.setAll(p);

	            anchors = new Anchor[3];
	            anchors[0] = new Anchor(points, 0);
	            anchors[1] = new Anchor(points, 2);
	            anchors[2] = new Anchor(points, 4);

	            configure(root, triangle);
	        }
	}

	public void start(Stage stage) {
		Group root = new Group();
		EditableShape triangle = new Triangle(root, 100d, 100d, 150d, 50d, 250d, 150d);
		Scene scene = new Scene(root, 600, 500, Color.BISQUE);
		stage.setTitle("EditableTriangle Manipulation Version 002");
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
