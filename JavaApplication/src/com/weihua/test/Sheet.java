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
public class Sheet {
    
    private boolean isVacant = true;
    private String name; 
    
    public synchronized void reserve (String name) {
        this.name = name;
        if (isVacant) {
            try {
                System.out.println(this.name + "予約開始");
                Thread.sleep(1000);
                System.out.println(this.name + "予約完了");
                this.isVacant = false;
                System.out.println(this.name + "で予約を受け付けました");
            } catch (InterruptedException ex) {
                Logger.getLogger(Sheet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
