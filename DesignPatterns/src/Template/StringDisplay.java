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
public class StringDisplay extends AbstractDisplay{

    private String msg;

    public StringDisplay(String msg) {
        this.msg = msg;
        System.out.println(msg.getBytes().length);
    }
    
    @Override
    public void open() {
        System.out.println(generateFrae("+"));
    }

    @Override
    public void print() {
        System.out.println("|" + this.msg + "|");
    }

    @Override
    public void close() {
        System.out.println(generateFrae("+"));
    }
    
    private String generateFrae(String corner) {
        String frame = corner;
        for (int i = 0; i < this.msg.getBytes().length; i++) {
            frame += "-";
        }
        frame += corner;
        
        return frame;
    }

}
