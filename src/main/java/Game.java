
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
        } catch(Exception e) {
            System.out.println("Error retrieving data, game closing.");
            return;
        }
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
            String guessRemaining = this.lives == 1 ? "guess remaining" : "guesses remaining";
            System.out.println("Guess a word ("+lives+" "+guessRemaining+"):");
            String guess = inputScanner.nextLine().trim().toLowerCase();
            while(guess.length() != wordLength || guess.contains(" ") || guess.contains("!") || guess.contains("_")){
                System.out.println("Guess must be " + wordLength +" letters");
                guess = inputScanner.nextLine().trim().toLowerCase();
            }
            check(guess);
            if(gameOver()){
                end();
                return;
            }
            //###TODOS##

            lives--;
        }
        System.out.println("Game over! The correct answer was: " + answer);
    }

    public void check(String guess){
        this.guesses.add(guess);
        System.out.println("Answer is : " + this.answer);
        this.currentGuess = "";
        String answerCopy = ""; //copy answer but replace correct guesses with _
        String[] guessArr = guess.split("");
        String[] answerArr = this.answer.split("");
        for (int i = 0; i < answerArr.length; i++){
            currentGuess += guessArr[i];
            answerCopy += guessArr[i].equals(answerArr[i]) ? "_" : answerArr[i];
            //if correct, replace letter w/ _ to skip in second loop.
        }
        String[] currentGuessArr = this.currentGuess.split("");

        //if answerArr[i].equals(_), leave guess as is
        //else, change the letter in guess to either ! for yellow or * for red
        for (int i = 0; i < answerArr.length; i++){
            if (answerCopy.split("")[i].equals("_")){
                continue;
            }
            currentGuessArr[i] = answerCopy.contains(currentGuessArr[i]) ? "!" : "*";
        }
        currentGuess = String.join("", currentGuessArr);
        guesses.add(currentGuess);

    }

    public boolean gameOver(){
        return this.currentGuess.equals(this.answer);
    }

    public void end(){
        String guessWord = this.lives == 1 ? "guess" : "guesses";
        System.out.println("Congratulations! You got it right with " + this.lives + " " + guessWord + " left.");
    }

    public void startMessage(){
        System.out.println("Welcome to Wordle_Clone! Here's how it works:\n" +
                "You have " + lives +" guesses to guess the word.\n" +
                "If your guess has the right letter in the right spot, the letter will appear in that spot\n" +
                "if your guess has the right letter in the wrong spot, the letter will become a '!'\n" +
                "if your guess has a letter that isn't in the answer at all, the letter will become a '*'\n" +
                "Good luck! :)\n");
    }

    public static void main(String[] args){
        Game game = new Game(5);
        game.startMessage();
        game.play();
    }
}