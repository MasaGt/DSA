/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory.framework;

/**
 *
 * @author Masaomi
 */
public abstract class Factory {

    public final Product craete(String owner) {
        Product p = createProduct(owner);
        registerproduct(p);
        return p;
    }

    protected abstract Product createProduct(String owner);
    
    protected abstract void registerproduct(Product p);

}
