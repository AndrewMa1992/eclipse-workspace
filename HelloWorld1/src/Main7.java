import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Main7 extends Application {
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

	class AnchorV2 extends Circle {
		DoubleProperty px, py;
		double offset_x, offset_y;

		public AnchorV2(DoubleProperty px, DoubleProperty py) {
			super(px.get(), py.get(), 10d);
			setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
			setStroke(Color.GOLD);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			this.px = px;
			this.py = py;

			setOnMousePressed((MouseEvent mouseEvent) -> {
				// record a delta distance for the drag and drop operation.
				offset_x = getCenterX() - mouseEvent.getX();
				offset_y = getCenterY() - mouseEvent.getY();
			});

			setOnMouseDragged((MouseEvent mouseEvent) -> {
				double newX = mouseEvent.getX() + offset_x;
				if (newX > 0 && newX < getScene().getWidth()) {
					setCenterX(newX);
					px.set(newX);
				}
				double newY = mouseEvent.getY() + offset_y;
				if (newY > 0 && newY < getScene().getHeight()) {
					setCenterY(newY);
					py.set(newY);
				}
			});
		}
	}

	abstract class EditableShape {
		protected AnchorV2[] anchors = null;

		protected void configure(Group root, Shape shape) {
			// Put shape and anchors into the JavaFX display group
			root.getChildren().add(shape);
			root.getChildren().addAll(anchors);

			// Set appearance for the shape
			shape.setStrokeWidth(4);
			shape.setStrokeLineCap(StrokeLineCap.ROUND);
			shape.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
			shape.setStroke(Color.FORESTGREEN);
			
			shape.setOnMouseClicked(event -> {
                for (AnchorV2 a : anchors) {
                    a.setVisible(!a.isVisible());
                }
            });
		}
	}

	class EditableTriangle extends EditableShape {
		private Polygon triangle;

		public EditableTriangle(Group root, Double... p) {
			triangle = new Polygon();
			ObservableList<Double> points = triangle.getPoints();
			points.setAll(p);

			anchors = new AnchorV2[3];
			for (int pi = 0; pi < 6; pi += 2) {
				final int pindex = pi;
				SimpleDoubleProperty px, py;

				px = new SimpleDoubleProperty(points.get(pi));
				px.addListener(pr -> points.set(pindex, px.get()));

				py = new SimpleDoubleProperty(points.get(pi + 1));
				py.addListener(pr -> points.set(pindex + 1, py.get()));

				anchors[pi / 2] = new AnchorV2(px, py);
			}

			configure(root, triangle);
		}
	}

	class EditableLine extends EditableShape {
		private Line line;

		public EditableLine(Group root, Double sx, Double sy, Double ex, Double ey) {
			line = new Line();
			line.setStartX(sx);
			line.setStartY(sy);
			line.setEndX(ex);
			line.setEndY(ey);

			anchors = new AnchorV2[2];
			anchors[0] = new AnchorV2(line.startXProperty(), line.startYProperty());
			anchors[1] = new AnchorV2(line.endXProperty(), line.endYProperty());

			configure(root, line);
		}
	}

	class EditableCircle extends EditableShape {
        private Circle circle;

        public EditableCircle(Group root, Double cx, Double cy, Double r) {
            circle = new Circle();
            circle.setCenterX(cx);
            circle.setCenterY(cy);
            circle.setRadius(60);

            anchors = new AnchorV2[2];
            anchors[0] = new AnchorV2(circle.centerXProperty(), circle.centerYProperty());
            anchors[1] = new AnchorV2(circle.radiusProperty(), circle.radiusProperty());

            configure(root, circle);
        }
    }
	public void start(final Stage stage) {

		Group root = new Group();

		EditableShape triangle = new EditableTriangle(root, 100d, 100d, 150d, 50d, 250d, 150d);
		EditableLine line = new EditableLine(root, 100d, 300d, 200d, 250d);
		EditableCircle circle = new EditableCircle(root, 300d, 300d, 60d);
		
		Scene scene = new Scene(root, 600, 500, Color.BISQUE);
		stage.setTitle("EditableTriangle Manipulation Version 002");
		stage.setScene(scene);

		scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.T) {
                EditableTriangle t = new EditableTriangle(root, 250d, 300d, 350d, 350d, 150d, 350d);
            }
        });
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
