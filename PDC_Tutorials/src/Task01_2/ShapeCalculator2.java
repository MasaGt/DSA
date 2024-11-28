/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task01_2;

import java.util.Scanner;

/**
 *
 * @author Masaomi
 */
public class ShapeCalculator2 {
    public static void main(String[] args) {
        
        System.out.println("Please select:\n(1) Rectangle\n(2) Circle\n(3) Square");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        try {
            int option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    System.out.println("width?");
                    input = scanner.nextLine();
                    double width = Double.parseDouble(input);
                    System.out.println("length?");
                    input = scanner.nextLine();
                    double length = Double.parseDouble(input);
                    Shape recObj = new Rectangle(width, length);
                    recObj.calculateArea();
                    recObj.printInfo();
                    break;

                case 2:
                    System.out.println("radius?");
                    input = scanner.nextLine();
                    double radius = Double.parseDouble(input);
                    Shape cirObj = new Circle(radius);
                    cirObj.calculateArea();
                    cirObj.printInfo();
                    break;
                    
                case 3:
                    System.out.println("side length?");
                    input = scanner.nextLine();
                    double sideLen = Double.parseDouble(input);
                    Shape squObj = new Square(sideLen);
                    squObj.calculateArea();
                    squObj.printInfo();
                    break;
                    
                default:
                    System.out.println("Wrong input");
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        } finally {
            scanner.close();
        }
        
    }
}
