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
public class CharDisplay extends AbstractDisplay{

    private char ch;
    
    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.println(this.ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }

}
