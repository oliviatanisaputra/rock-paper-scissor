import java.util.Random;
import java.util.Scanner;

public class trytry {
    public static void main(String[] args) {
        Random r = new Random();

        int random_int = r.nextInt(10) + 1;
        System.out.println("Random number: " + random_int);


        int a = 10; // int < double < Integer
        char myVar = 99; // a single character (char) ''

        System.out.println(myVar);

        Oot20242155_Mid1 new_rps = new Oot20242155_Mid1();
        Scanner scan = new Scanner(System.in);
        new_rps.player_input(scan);


    }
}
