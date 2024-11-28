/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interator;

/**
 *
 * @author Masaomi
 */
public class BookShelfIterator implements Iterator {

    private BookShelf shelf;
    private int index;

    public BookShelfIterator(BookShelf shelf) {
        this.shelf = shelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.shelf.getLength() > this.index;
    }

    @Override
    public Object next() {
        Book book = this.shelf.getBookAt(index);
        if (book == null) {
            //ここでErrorをthrow
            return null;
        } else {
            this.index++;
            return book;
        }
    }

}
