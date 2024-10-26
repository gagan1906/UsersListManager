package com.example.lab4;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class HelloApplication extends Application {

    private ObservableList<String> userList;
    private ArrayList<String> userData;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the data storage
        userData = new ArrayList<>();
        userData.addAll(Arrays.asList("Gagan", "Jashan", "Harsh", "Arsh"));
        userList = FXCollections.observableArrayList(userData);

        // Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS", 25));
        txtNote.setFill(Color.GREEN);
        txtNote.setWrappingWidth(300); // Ensure the text does not expand the layout too much

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Enter Name");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(85);

        // ListView and ObservableList
        ListView<String> listView = new ListView<>(userList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setMaxSize(300, 300);

        // Button Add
        btnAdd.setOnAction((ActionEvent t) -> {
            String input = fldAdd.getText();
            if (validateInput(input)) {
                userData.add(input);
                userList.setAll(userData);
                txtNote.setText("User added successfully");
                txtNote.setFill(Color.GREEN);
            } else {
                txtNote.setText("Invalid input! Name must start with uppercase, be at least 5 characters long, and not be empty.");
                txtNote.setFill(Color.RED);
            }
            fldAdd.clear();
        });

        // Button Delete
        btnDelete.setOnAction((ActionEvent t) -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                userData.remove(selectedItem);
                userList.setAll(userData);
                txtNote.setText("User removed successfully");
                txtNote.setFill(Color.GREEN);
            } else {
                txtNote.setText("No user selected for removal");
                txtNote.setFill(Color.RED);
            }
        });

        // Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnDelete);

        // Root Node
        BorderPane root = new BorderPane();
        root.setCenter(listView);
        root.setRight(right);
        root.setBottom(txtNote);

        // Fix the height of the bottom area to prevent layout shift
        BorderPane.setMargin(txtNote, new Insets(10));
        BorderPane.setAlignment(txtNote, Pos.CENTER);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("User List Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateInput(String input) {
        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0)) && input.length() >= 5;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
