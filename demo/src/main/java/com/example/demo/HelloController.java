package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

//    __________________________________________________________________________________________________
//    @FXML
//    private TableView<FoodQueue> tableView;
//
//    @FXML
//    private TableColumn<FoodQueue, Integer> queueOneColumn;
//
//    @FXML
//    private TableColumn<FoodQueue, Integer> queueTwoColumn;
//
//    @FXML
//    private TableColumn<FoodQueue, Integer> queueThreeColumn;
//
//    @FXML
//    private TableColumn<FoodQueue, Integer> waitingQueueColumn;
//
//    public void initialize() {
//        queueOneColumn.setCellValueFactory(new PropertyValueFactory<>("queueOne"));
//        queueTwoColumn.setCellValueFactory(new PropertyValueFactory<>("queueTwo"));
//        queueThreeColumn.setCellValueFactory(new PropertyValueFactory<>("queueThree"));
//        waitingQueueColumn.setCellValueFactory(new PropertyValueFactory<>("waitingQueue"));
//
//        // Retrieve the data and populate the TableView
//        FoodQueue[] foodQueueOne = new FoodQueue[2];
//        FoodQueue[] foodQueueTwo = new FoodQueue[3];
//        FoodQueue[] foodQueueThree = new FoodQueue[5];
//        FoodQueue[] waitingFoodQueue = new FoodQueue[10];
//
//        tableView.getItems().addAll(foodQueueOne);
//        tableView.getItems().addAll(foodQueueTwo);
//        tableView.getItems().addAll(foodQueueThree);
//        tableView.getItems().addAll(waitingFoodQueue);
//    }
}



