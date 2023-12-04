import java.util.Scanner;
import java.util.Random;

public class Numbergame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            playGame(random, scanner);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing. Goodbye!");
                break;
            }
        }

        scanner.close();
    }

    private static void playGame(Random random, Scanner scanner) {
        //Generate a random number within the specified range
        int secretNumber = random.nextInt(100) + 1;

        // Details about attempts in game
        int maxAttempts = 10;
        int attempts = 0;
        int roundsPlayed = 0;
        int score = 0;

        while (true) {
            // Prompt the user to enter their guess
            System.out.print("Guess the number (between 1 and 100): ");
            int userGuess;

            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
            userGuess = scanner.nextInt();

            // Compare the user's guess and provide feedback
            attempts++;
            if (userGuess == secretNumber) {
                System.out.println("Congrats! You guessed the correct number in " + attempts + " attempts.");
                score++;
                roundsPlayed++;
                break;
            } else if (userGuess < secretNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            // Checking if the user has reached the maximum number of attempts
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + secretNumber + ".");
                roundsPlayed++;
                break;
            }
        }

        // Additional details: Display the user's score
        System.out.println("Your score: " + score + "/" + roundsPlayed + " rounds won.");
    }
}