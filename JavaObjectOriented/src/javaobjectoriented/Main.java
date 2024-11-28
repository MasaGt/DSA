/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaobjectoriented;

import java.util.List;


/**
 *
 * @author Masaomi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mail mail = new Mail();
        List<MailItem> items = mail.get();
        
        for (MailItem item : items) {
            System.out.println("Title>>" + item.title);
            
            for (String msg : item.msgs) {
                System.out.println(msg);
            }
        }
    }
    
}
