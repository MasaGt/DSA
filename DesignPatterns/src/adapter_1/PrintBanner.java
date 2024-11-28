/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter_1;

/**
 *
 * @author Masaomi
 */
public class PrintBanner extends Banner implements Print{

    public PrintBanner(String msg) {
        super(msg);
    }

    @Override
    public void printWeak() {
        showWithP();
    }

    @Override
    public void printStrong() {
        showWIthA();
    }
    
}
