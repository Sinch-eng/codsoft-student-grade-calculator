import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGameV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        int rounds = 0;

        System.out.println(" Welcome to the Enhanced Number Guessing Game!");

        while (true) {
            System.out.println("\nChoose Difficulty Level:");
            System.out.println("1. Easy (1–50, 7 attempts)");
            System.out.println("2. Medium (1–100, 5 attempts)");
            System.out.println("3. Hard (1–200, 3 attempts)");
            System.out.print("Enter choice (1/2/3): ");

            int maxNumber = 100;
            int attemptsLeft = 5;

            int choice = scanner.nextInt();
            if (choice == 1) {
                maxNumber = 50;
                attemptsLeft = 7;
            } else if (choice == 3) {
                maxNumber = 200;
                attemptsLeft = 3;
            }

            int number = random.nextInt(maxNumber) + 1;
            rounds++;
            System.out.println("\nRound " + rounds + " started!");
            System.out.println("Guess the number between 1 and " + maxNumber + ".");

            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.print("\nAttempts left: " + attemptsLeft + " | Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess == number) {
                    System.out.println(" Correct! You guessed the number!");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess > number) {
                    System.out.println(" Too high! Try again.");
                } else {
                    System.out.println(" Too low! Try again.");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println(" Out of attempts! The correct number was: " + number);
            }

            System.out.println(" Current Score: " + score + " / " + rounds);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String again = scanner.next().toLowerCase();

            if (!again.equals("yes")) {
                System.out.println("\n Thanks for playing!");
                System.out.println("Final Score: " + score + " / " + rounds);
                break;
            }
        }

        scanner.close();
    }
}
