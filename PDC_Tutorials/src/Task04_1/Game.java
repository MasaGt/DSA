/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task04_1;

import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Masaomi
 */
public class Game {

    private final Player player;
    private final HashSet<Quiz> quizes;

    public Game(Player player) {
        this.quizes = new HashSet<>();
        this.player = player;
    }

    public void startQuiz() {

        Scanner sc = new Scanner(System.in);
        //generate 10 quizes
        while (quizes.size() < 10) {
            this.quizes.add(new Quiz());
        }

        System.out.println("Enter \'x\' to qit");

        //do quiz 10times
        for (Quiz q : this.quizes) {
            System.out.println(q.getNum1() + q.getOperation() + q.getNum2());
            boolean isValid = false;
            String strAnswer = "";

            do {
                System.out.println("Input your answer.");
                strAnswer = sc.nextLine();
            } while (!this.isValid(strAnswer));

            if (strAnswer.trim().equalsIgnoreCase("x")) {
                break;
            }
            this.player.addScore(checkAnswer(Double.parseDouble(strAnswer), q.getAns()));
        }
        sc.close();
    }

    public int checkAnswer(double uAnswer, double cAnswer) {
        int reward = 0;
        //Check the answer based on the absolute value of the difference between uAnswer and cAnswer. 
        if (Math.abs(cAnswer - uAnswer) < 0.0001) {
            reward = 10;
            System.out.println("Correct!");
        } else {
            reward = -10;
            System.out.println("Wrong!");
        }
        return reward;
    }
    
    public boolean isValid(String answer) {
        try {
            Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            if (!answer.equalsIgnoreCase("x")) {
                return false;
            }
        }
        return true;
    }

}
