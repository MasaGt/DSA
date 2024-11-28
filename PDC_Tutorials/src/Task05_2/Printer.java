/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task05_2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class Printer {

    int max = 9;
    boolean finishSpace = false;

    public synchronized void printStar(int i) {
        int j = 0;
        if (!finishSpace) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (j < (2 * i - 1)) {
            System.out.print("*");
            j++;
        }
        System.out.println();
        finishSpace = false;
        notify();
    }

    public synchronized void printSpace(int i) {
        int j = 0;
        if (finishSpace) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (j < (9 - i)) {
            System.out.print(" ");
            j++;
        }
        this.finishSpace = true;
        notify();
    }

    public static void main(String[] args) {
        Printer p = new Printer();

        Runnable space = new SpaceThread(p);
        Runnable star = new StarThread(p);

        Thread th1 = new Thread(space);
        Thread th2 = new Thread(star);

        th1.start();
        th2.start();
    }
}
