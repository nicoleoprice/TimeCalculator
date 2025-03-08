package model;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


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
    TableView<Time> table;

    //these are global variables to prevent erasing the existing table and instantiating a new linked list for calculations (it would not include existing data)
    //TimeList<Time> timeList = new TimeList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Time Calculator");

        /////Create Table
        table = new TableView<Time>();

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


        //Add data, text fields have default values to avoid null
        hoursInput = new TextField("00");
        minutesInput = new TextField("00");
        secondsInput = new TextField("00");
        Text colonOne = new Text(" : ");
        Text colonTwo = new Text(" : ");
        colonOne.resize(10, 18);

        hoursInput.setPrefWidth(100);
        minutesInput.setPrefWidth(100);
        secondsInput.setPrefWidth(100);

        //allow only numbers to be inputted
        hoursInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")) {
                    hoursInput.setText(t1.replaceAll("[^//d]", ""));
                }
            }
        });

        minutesInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")) {
                    minutesInput.setText(t1.replaceAll("[^//d]", ""));
                }
            }
        });

        secondsInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")) {
                    secondsInput.setText(t1.replaceAll("[^//d]", ""));
                }
            }
        });

        //add button and functionality
        Button add = new Button("Add");
        add.setOnAction(e -> addButtonClicked());

        //delete button and functionality
        Button delete = new Button("Delete");
        delete.setOnAction(e -> deleteButtonClicked());

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

        inputBox.getChildren().addAll(start, hoursInput, colonOne, minutesInput, colonTwo, secondsInput, region, add, end, delete);
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
        calculate.setOnAction(e -> calculateButtonClicked());

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

    public void addButtonClicked() {
        //create new Time object
        Time newTime = new Time();

        //set hours value to 0 if blank, then set the hours to the Time object hours
        if(hoursInput.getText().isBlank()) {
            hoursInput.setText("00");
        }
        newTime.setHours(Integer.parseInt(hoursInput.getText()));

        //set minutes value to 0 if blank, then set the minutes to Time object minutes
        if(minutesInput.getText().isBlank()) {
            minutesInput.setText("00");
        }
        newTime.setMinutes(Integer.parseInt(minutesInput.getText()));

        //set seconds to 0 if blank, then set the seconds to Time object seconds
        if(secondsInput.getText().isBlank()) {
            secondsInput.setText("00");
        }
        newTime.setSeconds(Integer.parseInt(secondsInput.getText()));

        //add Time object to table and linked list
        table.getItems().add(newTime);
        getTimeList().appendData(newTime);

        System.out.println(getTimeList().toString());

        //clear input fields
        hoursInput.clear();
        minutesInput.clear();
        secondsInput.clear();
    }

    public void deleteButtonClicked() {
        ObservableList<Time> allTimes = table.getItems();
        int toDeleteIndex = table.getSelectionModel().getSelectedIndex();

        //remove selected from the total list of Times in the table
        allTimes.remove(toDeleteIndex);
        table.getSelectionModel().clearSelection();

        //delete from linked list
        getTimeList().deleteData(toDeleteIndex);
        System.out.println(getTimeList().toString());
    }

    public void calculateButtonClicked() {
        //parse linked list to calculations
        TimeCalculations calculateTotal = new TimeCalculations(getTimeList());
        Time total = calculateTotal.toAdd();


        //display the totals
        hoursTotal.setText(String.valueOf(total.getHours()));
        minutesTotal.setText(String.valueOf(total.getMinutes()));
        secondsTotal.setText(String.valueOf(total.getSeconds()));
    }

    /**
     * If the timeList was a global variable, the timeList would be rewritten as an empty list after calculations.
     * If this code block was placed after the calculations, then the calculate button would add to the existing list doubling total within the table
     * It would be redundant to get the information from the displayed table and put it in a linked list
     *
     * The linked list is necessary for the calculations portion
     * @return current table's information as a linked list
     */
    public TimeList<Time> getTimeList() {
        ObservableList<Time> currentList = table.getItems();
        TimeList<Time> timeList = new TimeList<>();

        for (Time time : currentList) {
            timeList.appendData(time);
        }

        return timeList;
    }
}