import java.util.Scanner;
import java.util.Random;

public class TASK1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int roundsWon = 0;

        System.out.println("Welcome to the Customizable Number Guessing Game!");

        boolean playAgain;
        do {
            System.out.print("\nEnter the LOWER bound of the range: ");
            int lowerBound = getValidInt(scanner);

            System.out.print("Enter the UPPER bound of the range: ");
            int upperBound = getValidInt(scanner);
            while (upperBound <= lowerBound) {
                System.out.println("Upper bound must be greater than lower bound. Try again.");
                System.out.print("Enter the UPPER bound of the range: ");
                upperBound = getValidInt(scanner);
            }

            System.out.print("Enter the MAX number of attempts allowed: ");
            int maxAttempts = getValidInt(scanner);

            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attemptsLeft = maxAttempts;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nI've picked a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = getValidInt(scanner);

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    hasGuessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!hasGuessedCorrectly) {
                System.out.println(" You're out of attempts! The number was: " + numberToGuess);
            }

            totalRounds++;

            System.out.print("\nDo you want to play another round? (yes/no): ");
            scanner.nextLine(); 
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");

        } while (playAgain);

        System.out.println("\n Game Over! You played " + totalRounds + " round(s) and won " + roundsWon + " time(s).");
        scanner.close();
    }
    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
