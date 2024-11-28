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
public class Sample extends Thread{

    @Override
    public void run() {
        System.out.println("非同期内での処理です。");
    }
    
}
