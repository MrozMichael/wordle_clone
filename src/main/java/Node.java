import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
public class Node extends StackPane {

    private Rectangle rectangle;
    private String name;

    private Label label;
    public Node( String name, double x, double y, double width, double height) {

        // create rectangle
        this.rectangle = new Rectangle( width, height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);

        // create label
        this.name = name;
        this.label = new Label( this.name);

        // set position
        setTranslateX( x);
        setTranslateY( y);

        getChildren().addAll( rectangle, label);

    }

    public void changeColor(String color){
        rectangle.setStroke(Color.WHITE);
        switch(color){
            case "green":
                rectangle.setFill(Color.GREEN);
                break;
            case "yellow":
                rectangle.setFill(Color.YELLOW);
                break;
            default:
                rectangle.setFill(Color.GRAY);
                break;
        }
    }

    public void changeName(String newName){
        label.setText(newName);
    }
}