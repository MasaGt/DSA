/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task04_1;

import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class Quiz {

    private int num1;
    private int num2;
    private String operation;
    private double ans;
    private final static int BOUNDARY = 100;

    public Quiz() {
        this.init();
    }

    private void init() {
        //Generate two numbers between 0 to 100 randomly.
        this.num1 = this.generateNumber(BOUNDARY);
        this.num2 = this.generateNumber(BOUNDARY);
        this.operation = generateOperation();
        this.ans = calcAnswer();

//        System.out.println(num1 + operation + num2 + "=?");

    }

    private int generateNumber(int boundary) {
        return (new Random()).nextInt(boundary);
    }

    private String generateOperation() {

        String operation = "";

        switch (this.generateNumber(4)) {
            case 0:
                operation = "+";
                break;
            case 1:
                operation = "-";
                break;
            case 2:
                operation = "*";
                break;
            case 3:
                operation = "/";
                break;
            default:
                break;
        }

        return operation;
    }

    private double calcAnswer() {
        
        double ans = 0d;
        
        switch (this.getOperation()) {
            case "+":
                ans = this.getNum1() + this.getNum2();
                break;
            case "-":
                ans = this.getNum1() - this.getNum2();
                break;
            case "*":
                // In java, the result of an integer multiplied by an integer is still an integer.
                ans = 1d * this.getNum1() * this.getNum2();
                break;
            case "/":
                while (this.getNum2() == 0) {
                    this.num2 = generateNumber(BOUNDARY);
                }
                // In java, the result of an integer divided by an integer is still an integer.
                ans = 1d * this.getNum1() / this.getNum2();
                break;
        }
        
        return ans;
    }

    @Override
    public boolean equals(Object obj) {
        
        return obj != null && obj instanceof Quiz
                && this.getNum1() == ((Quiz)obj).getNum1()
                && this.getNum2() == ((Quiz)obj).getNum2()
                && this.getOperation().equals(((Quiz)obj).getOperation());
    }

    @Override
    public int hashCode() {
        return  (this.getNum1() + this.getOperation() + this.getNum2()).hashCode();
                
    }

    /**
     * @return the num1
     */
    public int getNum1() {
        return num1;
    }

    /**
     * @return the num2
     */
    public int getNum2() {
        return num2;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @return the ans
     */
    public double getAns() {
        return ans;
    }
}
