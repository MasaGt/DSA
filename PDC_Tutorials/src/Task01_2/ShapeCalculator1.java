/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task01_2;

/**
 *
 * @author Masaomi
 */
public class ShapeCalculator1 {
    
    public static void main(String[] args) {
        
        //calc circle
        Shape cirObj = new Circle(2.5);
        cirObj.calculateArea();
        
        //calc rectangle
        Shape recObj = new Rectangle(12, 16.5);
        recObj.calculateArea();
        
        //calc square
        Shape squObj = new Square(3.3);
        squObj.calculateArea();
        
        //print areas
        cirObj.printInfo();
        recObj.printInfo();
        squObj.printInfo();
    }
}
