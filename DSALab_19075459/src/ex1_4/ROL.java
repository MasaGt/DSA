/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1_4;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author wjh2989
 */
public class ROL<E> extends ArrayList<E> implements RandomObtainable<E> {

    private Random rand;

    public ROL() {
        rand = new Random();
    }
    
    @Override
    public E getRandom() throws NoSuchElementException {
        if (super.size() <= 0) {
            throw new NoSuchElementException();
        }
        
        return super.get(getRndIndx());
    }

    @Override
    public boolean removeRandom() throws UnsupportedOperationException {
        if (super.size() <= 0) {
            throw new UnsupportedOperationException();
        }
        
        super.remove(getRndIndx());
        return true;
    }
    
    private int getRndIndx() {
        return rand.nextInt(this.size());
    }

    @Override
    public String toString() {
        int index = 0;
        String msg = "[";
        for (E elem : this) {
            msg += elem;
            index++;
            if (index <= this.size() - 1) {
                msg += ",";
            }
        }
        return msg += "]";
    }
    
    
    
    public static void main(String[] args) {
        ROL<Integer> rom = new ROL();
        rom.add(1);
        rom.add(2);
        rom.add(3);
        rom.add(4);
        rom.add(5);
        rom.add(6);
        
        // print the contens
        System.out.println("--------The contents of ROL--------");
        System.out.println(rom);
        
        System.out.println("--------Remove an element randomly--------");
        //remove and show the contents each time
        int numTrial = 1;
        for (int i = rom.size(); i > 0; i--) {
            System.out.println(numTrial++ + "time");
            if (rom.removeRandom()) {
                System.out.println(rom);
            }
        }
    }
    
}
