/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory.idcard;

import Factory.framework.Factory;
import Factory.framework.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Masaomi
 */
public class IDcardFactory extends Factory{

    private List owners = new ArrayList();
    
    @Override
    protected Product createProduct(String owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void registerproduct(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
