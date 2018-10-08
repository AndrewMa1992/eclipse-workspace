
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWordVC3 extends Application {

	class ButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			System.out.println("Handler in inner class");
		}

	}

	int x = 0;

	@Override
	public void start(Stage primaryStage) {
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction((ActionEvent event) -> {
			System.out.println("Hello lambda " + x);
		});
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		x = 5;
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
