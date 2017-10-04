package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        VBox leftPane = new VBox(20);
        leftPane.backgroundProperty(Color.rgb(00,13,61));
        root.setLeft(leftPane);
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);

        VBox rightPane = new VBox(20);
        root.setRight(rightPane);
        rightPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);

        HBox topPane = new HBox(20);
        root.setTop(topPane);
        Button historyButton = new Button("History");
        historyButton.getStyleClass().add("normalButton");
        topPane.getChildren().add(historyButton);
        Button adjustStock= new Button("Adjust Stock");
        historyButton.getStyleClass().add("normalButton");
        topPane.getChildren().add(adjustStock);
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);

        HBox bottomPane = new HBox(20);
        root.setBottom(bottomPane);
        bottomPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(bottomPane, Pos.BOTTOM_CENTER);

        VBox centerPane = new VBox(20);
        root.setCenter(centerPane);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerPane, Pos.CENTER);

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setTitle("Main Window");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
