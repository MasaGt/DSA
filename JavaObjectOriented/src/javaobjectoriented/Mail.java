/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaobjectoriented;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Masaomi
 */
public class Mail extends ArrayList<String> {

    List<MailItem> get() {
        
        List<MailItem> res = new ArrayList<>();
        
        MailItem item1 = new MailItem();
        item1.title = "Title1";
        item1.msgs.add("Msg1");
        item1.msgs.add("Msg2");
        
        MailItem item2 = new MailItem();
        item2.title = "Title2";
        item2.msgs.add("MsgA");
        item2.msgs.add("MsgB");
        
        res.add(item1);
        res.add(item2);
        
        return res;
    }
}
