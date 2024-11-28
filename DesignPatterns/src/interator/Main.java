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
public class Main {
    
    private static final int SHELF_LEN = 4;
    
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();
        shelf.addBook(new Book("BOOK_A"));
        shelf.addBook(new Book("BOOK_B"));
        shelf.addBook(new Book("BOOK_C"));
        shelf.addBook(new Book("BOOK_D"));
        shelf.addBook(new Book("BOOK_E"));
        shelf.addBook(new Book("BOOK_F"));
        shelf.addBook(new Book("BOOK_G"));
        
        Iterator it = shelf.iterator();
        
        while (it.hasNext()) {
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
    }
}
