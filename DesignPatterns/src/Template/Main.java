/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

/**
 *
 * @author Masaomi
 */
public class Main {

    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('A');
        AbstractDisplay d2 = new StringDisplay("Hello, world.");
        AbstractDisplay d3 = new StringDisplay("こんにちは");
        
        d1.display();
        d2.display();
        d3.display();
    }
}
