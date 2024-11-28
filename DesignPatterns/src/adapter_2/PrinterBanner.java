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
public class PrinterBanner extends Printer{
    
    Banner banner;

    public PrinterBanner(String msg) {
        this.banner = new Banner(msg);
    }

    @Override
    public void showStrong() {
        this.banner.showWIthA();
    }

    @Override
    public void showWeak() {
        this.banner.showWithP();
    }
    
    
    
    
}
