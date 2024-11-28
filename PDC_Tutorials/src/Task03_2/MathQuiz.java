/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task03_2;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Masaomi
 */
public class MathQuiz {

    private static final String[] OPERATORS = {"+", "-", "*", "/"};
    private static int score = 0;

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Randomly generate 2 integers between 0 to 100
            int num1 = random.nextInt(101);
            int num2 = random.nextInt(101);

            //Randomly generate a math operation from +, -, * and /
            int index = random.nextInt(4);
            System.out.println(index);
            
            double answer = calc(num1, num2, index);

            //Display a math question
            System.out.println(num1 + " " + OPERATORS[index] + " " + num2 + " = ?");
            //Prompt user to input the answer
            System.out.println("Your answer");
            boolean isValid = false;
            while (!isValid) {
                try {
                    double input = checkAnswer(scanner);
                    isValid = true;
                    if (Double.compare(input, answer) == 0) {
                        score += 10;
                        System.out.println("Correct!");
                    } else {
                        score -= 10;
                        System.out.println("Incorrect...");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Your input is not valid");
                }
            }
        }

    }

    public static double calc(int num1, int num2, int index) {

        double result = 0;

        switch (index) {
            case 0:
                result = num1 + num2;
                break;
            case 1:
                result = num1 - num2;
                break;
            case 2:
                result = num1 * num2;
                break;
            case 3:
                result = 1d *  num1 / num2;
                break;
        }

        return result;
    }

    public static double checkAnswer(Scanner scanner) {

        String input = scanner.nextLine();

        if ("x".equalsIgnoreCase(input.trim())) {
            System.out.println("Your score is " + score);
            System.exit(0);
        }
        return Double.parseDouble(input);
    }
}
