package model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


/**
 * Nicole Price
 *
 * This project is designed for the user to be able to add multiple times together in hour:min:sec format
 *
 * The inspiration for this project was when I was trying to add times together, but since it's not the same as regular addition, it took a while.
 */
public class Main extends Application {
    TextField hoursInput;
    TextField minutesInput;
    TextField secondsInput;
    TextField hoursTotal, minutesTotal, secondsTotal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Time Calculator");

        /////Create Table
        TableView<Time> table = new TableView<>();

        //columns
        TableColumn<Time, Integer> hoursColumn = new TableColumn<>("Hours");
        hoursColumn.setCellValueFactory(new PropertyValueFactory<Time, Integer>("hours"));

        TableColumn<Time, Integer> minutesColumn = new TableColumn<>("Minutes");
        minutesColumn.setCellValueFactory(new PropertyValueFactory<Time, Integer>("minutes"));

        TableColumn<Time, Integer> secondsColumn = new TableColumn<>("Seconds");
        secondsColumn.setCellValueFactory(new PropertyValueFactory<Time, Integer>("seconds"));

        table.getColumns().add(hoursColumn);
        table.getColumns().add(minutesColumn);
        table.getColumns().add(secondsColumn);

        //I want the columns to be of equal width
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //End of Create Table


        //Add data
        hoursInput = new TextField();
        minutesInput = new TextField();
        secondsInput = new TextField();
        Text colonOne = new Text(" : ");
        Text colonTwo = new Text(" : ");
        colonOne.resize(10, 18);

        hoursInput.setPrefWidth(100);
        minutesInput.setPrefWidth(100);
        secondsInput.setPrefWidth(100);

        //allow only numbers to be inputted
        hoursInput.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        //create button
        Button add = new Button("Add");

        //create HBox (horizontal row) and add spacing
        HBox inputBox = new HBox();
        inputBox.setPadding(new Insets(20, 5, 20, 5));
        inputBox.setSpacing(5);
        Region region = new Region();
        Region start = new Region();
        Region end = new Region();

        HBox.setHgrow(region, Priority.ALWAYS);
        HBox.setHgrow(start, Priority.ALWAYS);
        HBox.setHgrow(end, Priority.ALWAYS);

        inputBox.getChildren().addAll(start, hoursInput, colonOne, minutesInput, colonTwo, secondsInput, region, add, end);
        //End of add data


        //Total time
        Text totalText = new Text("Total: ");
        hoursTotal = new TextField();
        minutesTotal = new TextField();
        secondsTotal = new TextField();
        Text colonThree = new Text(" : ");
        Text colonFour = new Text(" : ");

        //not editable since the calculated total will be outputted here
        hoursTotal.setEditable(false);
        minutesTotal.setEditable(false);
        secondsTotal.setEditable(false);

        hoursTotal.setPrefWidth(100);
        minutesTotal.setPrefWidth(100);
        secondsTotal.setPrefWidth(100);

        //calculate button
        Button calculate = new Button("Calculate");

        HBox totalBox = new HBox();
        totalBox.setPadding(new Insets(20, 5, 20, 5));
        totalBox.setSpacing(5);
        Region regionTotal = new Region();

        HBox.setHgrow(region, Priority.ALWAYS);

        totalBox.getChildren().addAll(totalText, hoursTotal, colonThree, minutesTotal, colonFour, secondsTotal, regionTotal, calculate);

        //end of total time box

        VBox box = new VBox();
        Text title = new Text("Time Calculator");
        title.setTextAlignment(TextAlignment.CENTER);

        box.getChildren().addAll(title, table, inputBox, totalBox);
        Scene scene = new Scene(box, 500, 500);

        //display scene
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMaxWidth(500);
        stage.show();
    }


}