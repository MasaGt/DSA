/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task05_2;

/**
 *
 * @author Masaomi
 */
public class SpaceThread implements Runnable {

    private Printer p;

    public SpaceThread(Printer p) {
        this.p = p;
    }
    
    
    @Override
    public void run() {
        for (int i = 1; i <= p.max; i++) {
            p.printSpace(i);
        }
    }

}
