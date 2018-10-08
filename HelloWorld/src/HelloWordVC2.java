

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWordVC2 extends Application{

	 @Override
	    public void start(Stage primaryStage) {
		 Button btn = new Button();
		 btn.setText("Say 'Hello World'");
		 Button btn2 = new Button();
	     btn2.setText("Say 'Hello from Button Two'");
	     StackPane root = new StackPane();
	     root.getChildren().add(btn2);
	     root.getChildren().add(btn);
	     Scene scene = new Scene(root, 300, 250);
	     primaryStage.setTitle("Hello World!");
	     primaryStage.setScene(scene);
	     primaryStage.show();
	    }
	 
	public static void main(String[] args) {
		
		launch(args);
	}

}
