package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

        Pane root = new Pane();
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Refund A Transaction");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

    }

    public void closeStage(Stage stage) {

        parent.setDisable(false);
        stage.close();

    }

}