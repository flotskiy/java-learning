package feb.first;

/*
Вывести на экран символы # так чтобы получилась лестница
     #
    ##
   ###
  ####
 #####
######
в зависимоти от высоты n. n > 0, n < 100;
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairway {
    public static void main(String[] args) {
        printStairway();
    }

    private static void printStairway() {
        while (true) {
            System.out.println("Please enter the number or type 'exit' to stop");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = null;
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("exit")) {
                System.out.println("Good bye :)");
                break;
            }
            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("This is not a number! Please try again!\n");
                continue;
            }
            if (number < 1 || number >= 100) {
                System.out.println("Input number is out of specified range (number > 1 and number < 100)\n" +
                        "Please try again!\n");
                continue;
            }

            System.out.println("Stairway for number = " + number + "\n");

            for (int i = 1; i <= number; i++) {
                for (int j = 0; j < number - i; j++) {
                    System.out.print(" ");
                }
                for (int k = 0; k < i; k++) {
                    System.out.print("#");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
