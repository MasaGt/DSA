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
public class Circle extends Shape{

    private double radius;
    
    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }
    
    @Override
    public void calculateArea() {
        this.area = this.radius * this.radius * Math.PI;
    }

}
