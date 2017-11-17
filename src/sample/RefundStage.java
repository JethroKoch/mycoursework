package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RefundStage {
    static Pane parent;

    public RefundStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        VBox root = new VBox();
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Refund A Transaction");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20));
        HBox.setHgrow(topPane, Priority.ALWAYS);
        root.getChildren().addAll(topPane);

        TextField productID = new TextField();
        productID.setPrefSize(Integer.MAX_VALUE,30);
        productID.


    }

    public void closeStage(Stage stage) {

        parent.setDisable(false);
        stage.close();

    }

}