import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.control.Label;
import java.awt.*;

public class Wordle_UI extends Application {
    private double sceneWidth = 1024;
    private double sceneHeight = 768;
    private Node[][] board;
    public static void main(String[] args) {
        //GUIGame.main(args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    int n = 5; //replace with user input letters/lives
    this.board = new Node[n][n];

    HBox top = createTop();
    GridPane grid = createGrid(n);
    BorderPane.setMargin(grid, new Insets(5, 5, 0, sceneWidth/4));
    root.setTop(top);
    root.setCenter(grid);
    Scene viewport = new Scene(root);
    primaryStage.setScene(viewport);
    primaryStage.setTitle("Wordle Clone");
    primaryStage.show();
    }

    public HBox createTop(){
        HBox top = new HBox();
        Label title = new Label("Wordle-Clone");
        top.getChildren().add(title);
        HBox.setMargin(title, new Insets(5, 5, 5,  sceneWidth/2));
        top.setPadding(new Insets(20, 20, 20, 20));
        top.setSpacing(10);
        top.setStyle("-fx-font-size: 50;");
        return top;
    }
    public GridPane createGrid(int n){
        GridPane grid = new GridPane();
        double gridWidth = sceneWidth / n;
        double gridHeight = sceneHeight/ n;
        for (int i = 0; i < n; i++){
            for (int k = 0; k < n; k++) {
                Node node = new Node("", i *gridWidth, k * gridHeight, gridWidth, gridHeight);
                grid.getChildren().add(node);
                board[i][k] = node;
            }
        }
        return grid;
    }
}
