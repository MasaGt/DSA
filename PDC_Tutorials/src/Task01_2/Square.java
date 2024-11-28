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
public class Square extends Rectangle{
    
    private double side;
    
    public Square(double side) {
        super(side);
        this.side = side;
    }

    @Override
    public void calculateArea() {
        this.area = this.side * this.side;
    }
    
    
}
