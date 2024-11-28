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
public class Main {
    
    public static void main(String[] args) {
        Print pb = new PrintBanner("hello");
        pb.printStrong();
        pb.printWeak();
    }
}
