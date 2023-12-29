
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
public class Wordle {

    private int wordLength;
    private String answer;
    private int lives;

    private int startingLives;

    private String currentGuess;
    private ArrayList<String> guesses;
    private Scanner inputScanner;
    public Wordle(int wordLength) {
        if (wordLength > 7 || wordLength < 5){
            System.out.println("Word length must be between 5 and 7");
            return;
        }
        this.wordLength = wordLength;
        this.lives = wordLength;
        this.startingLives = wordLength;
        this.guesses = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
        try {
            this.answer = getAnswer().toLowerCase();
        } catch(Exception e) {
            System.out.println("Error retrieving data, game closing.");
            return;
        }
    }
    public String getAnswer() throws FileNotFoundException {
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
            for(String guess: this.guesses){
                System.out.println(guess);
            }
            String guessRemaining = this.lives == 1 ? "guess remaining" : "guesses remaining";
            System.out.println("Guess a word ("+lives+" "+guessRemaining+"):");
            String guess = inputScanner.nextLine().trim().toLowerCase();
            while(guess.length() != wordLength || guess.contains(" ") || guess.contains("!") || guess.contains("_") || guess.contains("^")){
                System.out.println("Guess must be " + wordLength +" letters without spaces");
                guess = inputScanner.nextLine().trim().toLowerCase();
            }
            check(guess);
            if(gameOver()){
                end();
                return;
            }
            lives--;
        }
        System.out.println("Game over! The correct answer was: " + answer);
    }

    public void check(String guess){
        startGuess(guess);
        //System.out.println("Answer is : " + this.answer); //for debugging
        this.currentGuess = "";
        String answerCopy = ""; //copy answer but replace correct letters with _
        String[] guessArr = guess.split("");
        String[] answerArr = this.answer.split("");
        //1st loop to replace correct guesses with _
        for (int i = 0; i < answerArr.length; i++){
            currentGuess += guessArr[i];
            answerCopy += guessArr[i].equals(answerArr[i]) ? "_" : answerArr[i];
            //if correct, replace letter w/ _ to skip in second loop.
        }
        String[] currentGuessArr = this.currentGuess.split("");

        //if answerArr[i].equals(_), leave guess[i] as is
        //else, change the letter in guess to either ! for yellow or * for red
        for (int i = 0; i < answerArr.length; i++){
            if (answerCopy.split("")[i].equals("_")){
                continue;
            }
            if (answerCopy.contains(currentGuessArr[i])){
                answerCopy = answerCopy.replaceFirst(currentGuessArr[i], "^");
                currentGuessArr[i] = "!";
            } else{
                currentGuessArr[i] = "*";
            }
        }
        currentGuess = String.join("", currentGuessArr);
        finishGuess(currentGuess);
    }

    public boolean gameOver(){
        return this.currentGuess.equals(this.answer);
    }

    public void startGuess(String guess){
        int guessNumber = this.startingLives + 1 - this.lives;
        this.guesses.add("=========== Guess " + guessNumber + "===========");
        this.guesses.add("Your guess: " + guess);
    }

    public void finishGuess(String guess){
        this.guesses.add("Guess Result: " + guess);
        this.guesses.add("! means the letter is in a different spot\n* means the letter isn't in the answer");
        this.guesses.add("===============================");
    }

    public void end(){
        String guessWord = this.lives == 1 ? "guess" : "guesses";
        System.out.println("Congratulations! You got it right with " + this.lives + " " + guessWord + " left.");
    }

    public void startMessage(){
        System.out.println("\n*** Welcome to my Wordle Clone! ***\nHere's how it works:\n" +
                "You have " + lives +" guesses to guess the word\n" +
                "If your guess has the right letter in the right spot, the letter will appear in that spot\n" +
                "If your guess has the right letter in the wrong spot, the letter will become a '!'\n" +
                "If your guess has a letter that isn't in the answer at all, the letter will become a '*'\n" +
                "Good luck! :)\n");
    }

    public static void main(String[] args){
        System.out.println("Welcome to wordle_clone, please enter 5, 6, or 7 to decide how many letters " +
                "you want your word to have. An invalid input will default to a 5 letter word");
        Scanner wordLenScan = new Scanner(System.in);
        String input = wordLenScan.nextLine();
        int wordLen;
        try{
           wordLen = Integer.valueOf(input);
            } catch (Exception e){
            wordLen = 5;
        }
        Wordle game = new Wordle (wordLen);
        game.startMessage();
        game.play();
    }
}