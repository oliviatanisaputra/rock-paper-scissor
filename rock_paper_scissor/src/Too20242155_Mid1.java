import java.util.Scanner;
import java.util.Random;
//import java.util;

public class Too20242155_Mid1 {
    static int player_input(Scanner s) {
        // Ask for player's input and put it in p_input (string)
        System.out.println("Choices: rock, paper, scissor, quit");
        System.out.print("Choose one: ");
        String p_input = s.nextLine();

        // Then change it to number and put it in player (int)
        int player;
        switch (p_input) {
            case "rock":
                player = 0;
                break;
            case "paper":
                player = 1;
                break;
            case "scissor":
                player = 2;
                break;
            case "quit": // break key: -1
                player = -1;
                break;
            default: // If mistyped, key: -2
                player = -2;
        };
        return player;
    }


    static int computer_input() {
        // Generate computer's random "input" and put it in computer (int)
        Random random = new Random();
        int computer = random.nextInt(0, 3);
        return computer;
    }


    static int lose_or_win(int p, int c) {
        if (p == c) {
            return 1;
        } else if (p > c) {
            if (p == 2 && c == 0) {
                return 0;
            } else {
                return 2;
            }
        } else if (p == 0 && c == 2) {
            return 2;
        } else {
            return 0;
        } // Winning Keys: 0-lose, 1-tie, 2-win
    }


    static int[] scoring(int p_condition, int p_score, int c_score) {
        if (p_condition == 2) {
            p_score++;
        } else if (p_condition == 0) {
            c_score++;
        }
        return new int[] {p_score, c_score};
    }


    public static void main(String[] args) {
        String[] key = {"rock", "paper", "scissor"}; // These are the Choice Keys: rock-0, paper-1, scissor-2

        Scanner scanner = new Scanner(System.in);
        // We cannot close a Scanner object sin the method, or else it cannot loop. So put the scanner object here.

        boolean lever = true;
        int p_score = 0;
        int c_score = 0;

        while (lever) {
            // Get user input
            int player = player_input(scanner); // Call the scanner object and put it in the method.

            // Check if player wants to finish the game
            if (player == -1) {
                lever = false;
                scanner.close();
                continue;
            } else {
                // Check if player mistyped
                if (player == -2) {
                    System.out.println("It's not in the choice list. Please type more carefully ^^");
                } else {
                    System.out.print("Your choice: ");
                    System.out.println(key[player]);

                    // Generate computer's choice
                    int computer = computer_input();

                    System.out.print("Computer's choice: ");
                    System.out.println(key[computer]);

                    // Check who wins
                    int p_result = lose_or_win(player, computer);
                    int[] score = scoring(p_result, p_score, c_score);

                    // Input score
                    p_score = score[0];
                    c_score = score[1];

                    // Print result
                    String[] result_out = {"You lost (╥﹏╥)", "It's a draw (¬_¬)", "You won (＾▽＾)"};
                    System.out.println(result_out[p_result]);
                    System.out.println("----------");

                    // Score
                    System.out.print("Your Score: ");
                    System.out.println(p_score);
                    System.out.print("Computer's Score: ");
                    System.out.println(c_score);
                    System.out.println("====================");
                    System.out.println();
                }
            }
        }
    }
}
