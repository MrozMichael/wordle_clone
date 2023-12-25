
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
public class Game {
    private int wordLength;
    private String answer;
    private int lives;

    private String currentGuess;
    private ArrayList<String> guesses;
    private Scanner inputScanner;
    public Game(int wordLength) {
        if (wordLength > 7 || wordLength < 5){
            System.out.println("Word length must be between 5 and 7");
            return;
        }
        this.wordLength = wordLength;
        this.lives = 5;
        this.guesses = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
        try {
            this.answer = getAnswer(wordLength).toLowerCase();
        } catch(Exception e){
            System.out.println("Error retrieving data, game closing.");
            return;
        }
        this.currentGuess = answer.replaceAll("[a-z]","*" );
        this.guesses.add(currentGuess);
    }
    public String getAnswer(int wordLength) throws FileNotFoundException {
        String answer = "";
        try {
            File file = new File(wordLength+"letters.txt");
            ArrayList<String> words = new ArrayList<>();
            Scanner fileScan = new Scanner(file);
            while(fileScan.hasNextLine()){
                words.add(fileScan.nextLine());
            }
            fileScan.close();
            Random random = new Random();
            answer = words.get(random.nextInt(words.size()));
        } catch (Exception e){
            System.out.println("error: " + e.getMessage());
            return "";
        }

        return answer;
    }

    public void play(){
        while(lives > 0) {
            for(String guess: guesses){
                System.out.println(guess);
            }
            System.out.println("Guess a word:");
            String guess = inputScanner.nextLine().trim().toLowerCase();
            while(guess.length() != wordLength || guess.contains(" ")){
                System.out.println("Guess must be " + wordLength +" letters");
                guess = inputScanner.nextLine().trim().toLowerCase();
            }
            //###TODOS###
            //call check method to compare guess to answer
            //loop answer once to find greens, remove greens from answer
            //then loop again to find yellows
            //denote greens/yellows somehow
            //then add result to guesses arraylist
            //check if game over, if yes clean up
            //else lives-- and keep going;
            lives--;
        }
        System.out.println("Game over! The correct answer was: " + answer);
    }

    public static void main(String[] args){
        Game game = new Game(5);
        game.play();
    }
}