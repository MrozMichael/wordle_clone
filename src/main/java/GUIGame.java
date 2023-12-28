import javafx.application.Application;
import javafx.scene.layout.GridPane;
import java.lang.Math.*;
//Implement Wordle Game logic for Javafx app Wordle_UI
public class GUIGame {

    public static void main(String[] args){

    }

    public void playGame(Node[][] gameBoard){
        int n = gameBoard.length; //word length/number of guesses
        CLIGame gameLogic = new CLIGame(n); //not elegant, but using previously created CLIGame to handle what it can
        String answer = fetchAnswer(gameLogic);
        System.out.println(answer);
        while(n > 0){
            //on enter key, check the guess
            n--;
        }
    }

    public String fetchAnswer(CLIGame gameLogic){
        String output = "";
        try {
            output = gameLogic.getAnswer();
        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
            return null;
        }
        return output;
    }

}
