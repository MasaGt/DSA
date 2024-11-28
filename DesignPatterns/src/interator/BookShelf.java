/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Masaomi
 */
public class BookShelf implements Aggregate{
    
    //private Book[] books;
    private List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<>();
    }
    
    public Book getBookAt(int index) {
        if (index >= this.books.size()) {
            return  null;
        } else {
            return this.books.get(index);
        }
        
    }
    
    public void addBook(Book book) {
        this.books.add(book);
    }
    
    public int getLength() {
        return this.books.size();
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
    
    
}
