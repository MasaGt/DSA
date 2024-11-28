package Task08_2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookStoreMain {

    public static void main(String[] args) {

        BookStore bookstore = new BookStore();
        bookstore.createPromotionTable();
        ResultSet rs = bookstore.getWeekSpecial();
        bookstore.createWeekSpecialTable(rs);
        
    }

}
