/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weihua.test;


/**
 *
 * @author Masaomi
 */
public class Sample2 implements Runnable{

    private Sheet sheet;
    private String name;
    
    public Sample2(Sheet sheet, String name) {
        this.sheet = sheet;
        this.name = name;
    }
    
    @Override
    public void run() {
        sheet.reserve(name);
    }
}
