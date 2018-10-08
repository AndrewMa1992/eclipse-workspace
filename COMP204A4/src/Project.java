



import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project extends Application{
	ObservableList<Item> observable = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	
    	GridPane grid = new GridPane();

        final TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(600);
        grid.add(textArea, 2, 1);

        VBox vbox = new VBox();
        Button button = new Button("Submit");
        button.setPrefWidth(300);
        vbox.getChildren().add(button);
        grid.add(vbox, 0, 1);

        addNode(grid, vbox, button);

        TableView<Item> table = new TableView<Item>();
        table.setPrefWidth(300);
        table.setEditable(true);
        TableColumn item1 = new TableColumn("Name");
        item1.setCellValueFactory(
                new PropertyValueFactory<Item, String>("name"));
        table.getColumns().addAll(item1);
        TableColumn item2 = new TableColumn("Quantity");
        item2.setCellValueFactory(
                new PropertyValueFactory<Item, String>("quantity"));
        table.getColumns().addAll(item2);
        TableColumn item3 = new TableColumn("Unitcost");
        item3.setCellValueFactory(
                new PropertyValueFactory<Item, String>("unitCost"));
        table.getColumns().addAll(item3);
        TableColumn item4 = new TableColumn("Totalcost");
        item4.setCellValueFactory(
                new PropertyValueFactory<Item, String>("totalCost"));
        table.getColumns().addAll(item4);
        table.setItems(observable);
        grid.add(table, 1, 1);

        observable.addListener(new ListChangeListener<Item>() {
            @Override
            public void onChanged(
                    javafx.collections.ListChangeListener.Change<? extends Item> c) {
                print(textArea);
            }

        });

        //stage setting part
        Scene scene = new Scene(grid, 900, 600);
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();
    }
    
   
    
    
    private void addNode(GridPane grid, VBox vbox, Button button) {
    	
    	HBox h1 = new HBox();
        Label l1 = new Label("Product name");
        l1.setPrefWidth(100);
        final TextField t1 = new TextField();
        h1.getChildren().add(l1);
        h1.getChildren().add(t1);

        HBox h2 = new HBox();
        Label l2 = new Label("Product quantity");
        l2.setPrefWidth(100);
        final TextField t2 = new TextField();
        h2.getChildren().add(l2);
        h2.getChildren().add(t2);

        HBox h3 = new HBox();
        Label l3 = new Label("Unit cost");
        l3.setPrefWidth(100);
        final TextField t3 = new TextField();
        h3.getChildren().add(l3);
        h3.getChildren().add(t3);

        HBox h4 = new HBox();
        Label l4 = new Label("Total cost");
        l4.setPrefWidth(100);
        final TextField t4 = new TextField();
        h4.getChildren().add(l4);
        h4.getChildren().add(t4);

        vbox.getChildren().add(h1);
        vbox.getChildren().add(h2);
        vbox.getChildren().add(h3);
        vbox.getChildren().add(h4);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pattern p1 = Pattern.compile("[0-9]*");
                Pattern p2 = Pattern.compile("[0-9]*\\.[0-9]*");
                if (!p1.matcher(t2.getText()).matches()) {
                    t2.setText("");
                }
                if (!p2.matcher(t3.getText()).matches()) {
                    t3.setText("");
                }
                if (!p2.matcher(t4.getText()).matches()) {
                    t4.setText("");
                }
                if (!t1.getText().isEmpty() && !t2.getText().isEmpty()
                        && !t3.getText().isEmpty() && !t4.getText().isEmpty()) {
                    observable.add(new Item(t1.getText(),
                            Integer.parseInt(t2.getText()),
                            Float.parseFloat(t3.getText()),
                            Float.parseFloat(t4.getText())));
                }
            }
        });
        t2.setOnKeyReleased(new EventHandler<Event>() {
            public void handle(Event event) {
                if (!t2.getText().isEmpty() && !t3.getText().isEmpty()) {
                    t4.setStyle("");
                    t4.setText(Integer.parseInt(t2.getText())
                            * Float.parseFloat(t3.getText()) + "");
                }
            }
        });
        t3.setOnKeyReleased(new EventHandler<Event>() {
            public void handle(Event event) {
                if (!t2.getText().isEmpty() && !t3.getText().isEmpty()) {
                    t4.setStyle("");
                    t4.setText(Integer.parseInt(t2.getText())
                            * Float.parseFloat(t3.getText()) + "");
                }
            }
        });
        t4.setOnKeyReleased(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (!t2.getText().isEmpty() && !t3.getText().isEmpty()) {
                    float total = Integer.parseInt(t2.getText())
                            * Float.parseFloat(t3.getText());
                    float now = Float.parseFloat(t4.getText());
                    if (total != now) {
                        t4.setStyle("-fx-text-fill: yellow;");
                    } else {
                        t4.setStyle("");
                    }
                }
            }
        });
    }
    
    public final void print(TextArea textArea) {
        textArea.clear();
        float total = 0;
        for (int i = 0; i < observable.size(); i++) {
            total += observable.get(i).getTotalCost();
            textArea.appendText(observable.get(i).toString() + "\r\n");
            textArea.appendText("\r\n");
        }
        textArea.appendText("Total:" + total);
    }
    public static class Item {
        public final SimpleStringProperty name;
        public final SimpleIntegerProperty quantity;
        public final SimpleFloatProperty unitCost;
        public final SimpleFloatProperty totalCost;

        //constructor

        public Item(String name, int quantity, float unitCost,
                    float totalCost) {
            this.name = new SimpleStringProperty(name);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.unitCost = new SimpleFloatProperty(unitCost);
            this.totalCost = new SimpleFloatProperty(totalCost);
        }
       //getter and setter
        public String getName() {
            return this.name.get();
        }
        public int getQuantity() {
            return this.quantity.get();
        }
        public float getUnitCost() {
            return this.unitCost.get();
        }

        public float getTotalCost() {
            return this.totalCost.get();
        }

        //tostring part for printing something
        @Override
        public String toString() {
            if (getQuantity() * getUnitCost() != getTotalCost()) {
                return "Name=" + name.get() + "\r\nQuantity=" + quantity.get()
                        + "\r\nUnitCost=" + unitCost.get() + "\r\n*TotalCost="
                        + totalCost.get();
            } else {
                return "Name=" + name.get() + "\r\nQuantity=" + quantity.get()
                        + "\r\nUnitCost=" + unitCost.get() + "\r\nTotalCost="
                        + totalCost.get();
            }
        }

    }
}
