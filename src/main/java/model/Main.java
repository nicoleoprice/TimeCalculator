package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Nicole Price
 *
 * This project is designed for the user to be able to add multiple times together in hour:min:sec format
 *
 * The inspiration for this project was when I was trying to add times together, but since it's not the same as regular addition, it took a while.
 */
public class Main extends Application {

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

        //End of Create Table

        VBox box = new VBox();
        Text title = new Text("Time Calculator");

        box.getChildren().addAll(title, table);
        Scene scene = new Scene(box, 300, 400);

        //display scene
        stage.setScene(scene);
        stage.show();
    }


}