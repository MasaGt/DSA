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
public class Rectangle extends Shape{

    private double width;
    private double length;
    
    public Rectangle(double width, double length) {
        super("Rectangle");
        this.width = width;
        this.length = length;
    }
    
    public Rectangle(double side) {
        super("Square");
        this.length = side;
        this.width = side;
    }

    @Override
    public void calculateArea() {
       this.area = this.width * this.length;
    }    
}
