import javafx.application.Application;
import javafx.stage.Stage;

public class HelloWorldVC1 extends Application {
	@Override
	public void start(Stage basicStage) {
		basicStage.show();
		Stage secondStage = new Stage();
		secondStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
