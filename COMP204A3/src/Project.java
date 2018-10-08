

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Project extends Application{

	TextField total1 = new TextField();
    TextField total2 = new TextField();
    TextField total3 = new TextField();
    TextField total = new TextField();

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	 primaryStage.setTitle("JavaFX Project");
         GridPane grid = new GridPane();
         grid.setAlignment(Pos.TOP_LEFT);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));

         Separator separator = new Separator();
         separator.setOrientation(Orientation.VERTICAL);
         separator.setPrefHeight(300);
         GridPane.setConstraints(separator, 6, 3);
         GridPane.setRowSpan(separator, 4);
         grid.getChildren().add(separator);

         Label l1 = new Label("");
         GridPane.setColumnSpan(l1, 10);
         grid.add(l1, 8, 3);
         total1.setPrefColumnCount(5);
         grid.add(total1, 20, 3);

         Label l2 = new Label("");
         GridPane.setColumnSpan(l2, 10);
         grid.add(l2, 8, 4);
         total2.setPrefColumnCount(5);
         grid.add(total2, 20, 4);

         Label l3 = new Label("");
         GridPane.setColumnSpan(l3, 10);
         grid.add(l3, 8, 5);
         total3.setPrefColumnCount(5);
         grid.add(total3, 20, 5);

         Label t = new Label("Total:");
         grid.add(t, 15, 6);
         total.setPrefColumnCount(5);
         grid.add(total, 20, 6);


         TextField item1 = new TextField();
         grid.add(item1, 1, 3);
         TextField num1 = new TextField();
         num1.setPrefColumnCount(2);
         grid.add(num1, 2, 3);
         TextField price1 = new TextField();
         price1.setPrefColumnCount(5);
         grid.add(price1, 3, 3);

         TextField item2 = new TextField();
         grid.add(item2, 1, 4);
         TextField num2 = new TextField();
         num2.setPrefColumnCount(2);
         grid.add(num2, 2, 4);
         TextField price2 = new TextField();
         price2.setPrefColumnCount(5);
         grid.add(price2, 3, 4);

         TextField item3 = new TextField();
         grid.add(item3, 1, 5);
         TextField num3 = new TextField();
         num3.setPrefColumnCount(2);
         grid.add(num3, 2, 5);
         TextField price3 = new TextField();
         price3.setPrefColumnCount(5);
         grid.add(price3, 3, 5);
         action(item1, num1, price1, l1, total1);
         action(item2, num2, price2, l2, total2);
         action(item3, num3, price3, l3, total3);

         Scene scene = new Scene(grid, 700, 300);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
    private void action(final TextField item, final TextField num, final TextField price, final Label label,
            final TextField t) {
    	
    	item.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                label.setText(num.getText() + "x " + item.getText());
                int size = num.getText().equals("") ? 0 : Integer.parseInt(num.getText());
                double pri = price.getText().equals("") ? 0 : Double.parseDouble(price.getText());
                t.setText(size * pri + "");
                double t1 = total1.getText().equals("") ? 0 : Double.parseDouble(total1.getText());
                double t2 = total2.getText().equals("") ? 0 : Double.parseDouble(total2.getText());
                double t3 = total3.getText().equals("") ? 0 : Double.parseDouble(total3.getText());
                total.setText((t1 + t2 + t3) + "");
            }
        });
    	
    	num.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                label.setText(num.getText() + "x " + item.getText());
                int size = num.getText().equals("") ? 0 : Integer.parseInt(num.getText());
                double pri = price.getText().equals("") ? 0 : Double.parseDouble(price.getText());
                t.setText(size * pri + "");
                double t1 = total1.getText().equals("") ? 0 : Double.parseDouble(total1.getText());
                double t2 = total2.getText().equals("") ? 0 : Double.parseDouble(total2.getText());
                double t3 = total3.getText().equals("") ? 0 : Double.parseDouble(total3.getText());
                total.setText((t1 + t2 + t3) + "");
            }
        });
    	
    	price.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                label.setText(num.getText() + "x " + item.getText());
                int size = num.getText().equals("") ? 0 : Integer.parseInt(num.getText());
                double pri = price.getText().equals("") ? 0 : Double.parseDouble(price.getText());
                t.setText(size * pri + "");
                double t1 = total1.getText().equals("") ? 0 : Double.parseDouble(total1.getText());
                double t2 = total2.getText().equals("") ? 0 : Double.parseDouble(total2.getText());
                double t3 = total3.getText().equals("") ? 0 : Double.parseDouble(total3.getText());
                total.setText((t1 + t2 + t3) + "");
            }
        });
    }
}
