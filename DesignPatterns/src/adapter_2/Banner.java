/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter_2;

/**
 *
 * @author Masaomi
 */
public class Banner {

    private String msg;

    public Banner(String msg) {
        this.msg = msg;
    }

    public void showWithP() {
        System.out.println("(" + this.msg + ")");
    }
    
    public void showWIthA() {
        System.out.println("*" + this.msg + "*");
    }
}
