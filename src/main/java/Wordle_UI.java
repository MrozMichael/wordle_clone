import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Wordle_UI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    GridPane root = new GridPane();
    Scene viewport = new Scene(root);
    primaryStage.setScene(viewport);
    primaryStage.setTitle("Wordle Clone");
    primaryStage.show();
    }
}
