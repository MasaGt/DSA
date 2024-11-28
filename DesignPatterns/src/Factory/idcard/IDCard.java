/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory.idcard;

import Factory.framework.Product;

/**
 *
 * @author Masaomi
 */
public class IDCard extends Product{

    private String owner;

    public IDCard(String owner) {
        this.owner = owner;
    }
    
    @Override
    public void use() {
        System.out.println(this.owner + "のカードを使います");
    }
    
    public String hetOwwner() {
        return this.owner;
    }
    
}
