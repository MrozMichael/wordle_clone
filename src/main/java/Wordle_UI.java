import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.control.Label;

public class Wordle_UI extends Application {
    private double sceneWidth = 1024;
    private double sceneHeight = 768;
    private Node[][] board;

    private int wordLen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        BorderPane startRoot = new BorderPane();
        VBox elements = new VBox();
        elements.setStyle("-fx-border-color: black");
        Label welcomeText = new Label("Select word size");
        configureLabel(welcomeText);
        HBox buttons = new HBox();

        Button five = new Button("5-Letter Word");
        Button six = new Button("6-Letter Word");
        Button seven = new Button("7-Letter Word");

        buttons.getChildren().addAll(five, six, seven);
        configureButtons(buttons);
        elements.getChildren().addAll(welcomeText, buttons);
        startRoot.setCenter(elements);
        Scene startScene = new Scene(startRoot);
        stage.setScene(startScene);
        configureStartWindow(stage);
        stage.setTitle("Wordle Clone");
        stage.show();
        seven.setOnAction((event) -> {
            wordLen = 7;
            changeScene(stage);
        });
        six.setOnAction((event) -> {
            wordLen = 6;
            changeScene(stage);
        });
        five.setOnAction((event) -> {
            wordLen = 5;
            changeScene(stage);
        });

    }

    public void configureStartWindow(Stage stage){
        stage.setMinHeight(250);
        stage.setMinWidth(500);
        stage.setMaxHeight(250);
        stage.setMaxWidth(500);
    }

    public void configureGameWindow(Stage stage){
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.setMaxHeight(Double.MAX_VALUE);
        stage.setMaxWidth(Double.MAX_VALUE);
    }

    public HBox createTop(){
        HBox top = new HBox();
        Label title = new Label("Wordle-Clone");
        top.getChildren().add(title);
        HBox.setMargin(title, new Insets(5, 5, 5,  sceneWidth/4));
        top.setPadding(new Insets(20, 20, 20, 20));
        top.setSpacing(10);
        top.setStyle("-fx-font-size: 50;");
        return top;
    }
    public GridPane createGrid(int n){
        GridPane grid = new GridPane();
        double gridWidth = 80;
        double gridHeight = 80;
        for (int i = 0; i < n; i++){
            for (int k = 0; k < n; k++) {
                Node node = new Node("", i *gridWidth, k * gridHeight, gridWidth, gridHeight);
                grid.getChildren().add(node);
                board[i][k] = node;
            }
        }
        return grid;
    }
    public void configureLabel(Label label){
        label.setMinHeight(50);
        label.setMinWidth(80);
        label.setStyle("-fx-font-size: 40;");
        VBox.setMargin(label, new Insets(50, 0, 0, 100));
    }

    public void configureButtons(HBox buttons){
        VBox.setMargin(buttons, new Insets(50, 0, 0, 100) );
    }

    public void changeScene(Stage stage){
        BorderPane mainRoot = new BorderPane();
        int n = wordLen; //replace with user input letters/lives
        this.board = new Node[n][n];
        HBox top = createTop();
        GridPane grid = createGrid(n);
        BorderPane.setMargin(grid, new Insets(5, 5, 0, sceneWidth/5));
        mainRoot.setTop(top);
        mainRoot.setCenter(grid);
        Scene gameScene = new Scene(mainRoot);
        configureGameWindow(stage);
        stage.setScene(gameScene);
        GUIGame game = new GUIGame();
        game.playGame(getBoard());
    }

    public Node[][] getBoard(){
        return board;
    }

    public void setNode(Node node){

    }
}
