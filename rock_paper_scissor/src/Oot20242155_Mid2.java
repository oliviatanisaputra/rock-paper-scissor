import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Oot20242155_Mid2 {
    static Scanner scanner = new Scanner(System.in); // global variable/object

    static int player_input(int[] life) {
        // Ask for player's input and put it in p_input (string)
        System.out.print("Choose one: ");
        String p_input = scanner.nextLine();
        System.out.println(". . . . .");

        // Then change it to number and put it in player (int)
        // Check if player typed what is not in the available list
        int player;
        switch (p_input) {
            case "rock":
                if (life[0] == 1) {
                    player = 0;
                } else {
                    player = -2;
                }
                break;
            case "paper":
                if (life[1] == 1) {
                    player = 1;
                } else {
                    player = -2;
                }
                break;
            case "scissor":
                if (life[2] == 1) {
                    player = 2;
                } else {
                    player = -2;
                }
                break;
            default: // If mistyped, key: -2
                player = -2;
        }
        return player;
    }


    static int computer_input(int[] life) {
        // Generate computer's random "input" and put it in computer (int)
        Random random = new Random();
        int computer = random.nextInt(0, 3);

        while (life[computer] == 0) {
            computer = random.nextInt(0, 3);
        }
        return computer;
    }


    static int check_alive(int[] cardlist) {
        int c = 0;
        for (int i = 0; i < 3; i++) {
            if (cardlist[i] == 1) {
                c++;
            }
        }
        int possibility;
        switch (c) {
            case 0: // No "card" left: cannot play, game over
                possibility = -1;
                break;
            case 1: // One "card" left: to check for draw or not
                possibility = 0;
                break;
            default:
                possibility = 1;
                break;
        }
        return possibility;
    }


    static int lose_or_win(int subject, int comparison) {
        if (subject == comparison) {
            return 1;
        } else if (subject > comparison) {
            if (subject == 2 && comparison == 0) {
                return 0;
            } else {
                return 2;
            }
        } else if (subject == 0 && comparison == 2) {
            return 2;
        } else {
            return 0;
        } // Winning Keys: 0-lost, 1-tie, 2-win
    }


    static int[] cards_update(int result, int choice, int[] cardlist) {
        if (result == 0) {
            cardlist[choice] = 0;
        }
        return cardlist;
    }


    public static void main(String[] args) {
        String[] key = {"rock", "paper", "scissor"}; // These are the Choice Keys: rock-0, paper-1, scissor-2

        int p_score = 0;
        int c_score = 0;

        // There will be a total of 3 rounds
        for (int i=0; i<3; i++) {
            // Availability for each
            int[] p_cards = {1, 1, 1}; // The order is the same as the Choice Keys
            int[] c_cards = {1, 1, 1};

            // Check if player and computer have at least one card or not
            boolean canplay = true;

            System.out.print("ROUND ");
            System.out.print(i+1);
            System.out.println(" (•̀ᴗ•́)و");

            while (canplay) {
                // Inform available cards for player
                System.out.print("You have: ");
                for (int j=0; j<3; j++) {
                    if (p_cards[j] == 1) {
                        System.out.print(key[j]);
                        System.out.print(" ");
                    }
                }
                System.out.println();

                // Ask player's input
                int player = player_input(p_cards);
//                System.out.println(player);

                if (player == -2) {
                    System.out.println("It's not in the choice list. Please type more carefully ^^");
                } else {
                    System.out.print("Your choice: ");
                    System.out.println(key[player]);

                    // Generate computer's input
                    int computer = computer_input(c_cards);
//                    System.out.println(computer);

                    System.out.print("Computer's choice: ");
                    System.out.println(key[computer]);

                    // Check who wins
                    int p_result = lose_or_win(player, computer);
                    int c_result = lose_or_win(computer, player);

                    String[] result_out = {"You lost (╥﹏╥)", "It's a draw (¬_¬)", "You won (＾▽＾)"};
                    System.out.println(result_out[p_result]);
                    System.out.println("----------");

                    // Update card list. Remove card if one lost
                    p_cards = cards_update(p_result, player, p_cards);
                    c_cards = cards_update(c_result, computer, c_cards);

//                    System.out.println(Arrays.toString(p_cards));
//                    System.out.println(Arrays.toString(c_cards));

                    // Check if..
                    // 1. either player or computer still has at least one card: one wins
                    // 2. both has one same card: draw
                    if ((check_alive(p_cards) == -1) || (check_alive(c_cards) == -1)) {
                        if (check_alive(c_cards) == -1) {
                            p_score++;
                            System.out.println("The computer has no card left.");
                            System.out.println("You won this round!! ＼(＾O＾)／ ");
                            System.out.println("====================");
                            System.out.println();
                        } else {
                            c_score++;
                            System.out.println("You have no card left.");
                            System.out.println("You lost this round!! (x_x)");
                            System.out.println("====================");
                            System.out.println();
                        }
                        canplay = false;
                    } else if ((check_alive(p_cards) == 0) && (Arrays.equals(p_cards, c_cards))) {
                        System.out.println("No one won this round. It's a tie for this round! (・_・)ノ");
                        System.out.println("====================");
                        System.out.println();
                        canplay = false;
                    }
                }
            }
        }
        scanner.close();

        System.out.println("THE GAME HAS FINISHED!");
        if (p_score > c_score) {
            System.out.println("Congratulations you have won this game!! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
        } else if (p_score == c_score) {
            System.out.println("Your score and the computer is the same. (・_・;)");
        } else {
            System.out.println("You have lost this game (ಥ﹏ಥ)");
            System.out.println("Apparently the computer has more luck than you.");
        }

        System.out.println();
        System.out.print("Your total score: ");
        System.out.println(p_score);
        System.out.print("The computer's total score: ");
        System.out.println(c_score);
    }
}