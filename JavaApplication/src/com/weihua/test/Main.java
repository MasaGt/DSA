/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weihua.test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class Main {
    
    public static void main(String[] args) {
        
       Sheet sheet = new Sheet();
        
       Runnable r1 = new Sample2(sheet, "サンプル1");
       Thread th1 = new Thread(r1);
       
       Runnable r2 = new Sample2(sheet, "サンプル2");
       Thread th2 = new Thread(r2);
       
       th1.start();
       th2.start();
       
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println("main終了");
    }
}
