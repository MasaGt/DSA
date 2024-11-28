package Task08_2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookStore {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public BookStore() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        System.out.println(dbManager.getConnection());
    }

    public void connectBookStoreDB() {
        //use the conn, initialize database by creating BOOK Table and insert records
    }

    public void createPromotionTable() {
        
            /* You may need the following SQL statements for this method:
            
            * Create the table:
            CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT);
            
            * Insert records into the table:
            INSERT INTO PROMOTION VALUES ('Fiction', 0),
            ('Non-fiction', 10),
            ('Textbook', 20);
            
            */
        try {    
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT)");
            statement.executeUpdate("INSERT INTO PROMOTION VALUES ('Fiction', 0), ('Non-fiction', 10), ('Textbook', 20)");
        } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getWeekSpecial() {
        /* You may need the following SQL statements for this method:

        * Query multiple tables:
        
          SELECT TITLE, PRICE, DISCOUNT FROM BOOK, PROMOTION WHERE BOOK.CATEGORY=PROMOTION.CATEGORY;

         */
        
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT TITLE, PRICE, DISCOUNT FROM BOOK, PROMOTION WHERE BOOK.CATEGORY=PROMOTION.CATEGORY");
        } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);

    }

    public void createWeekSpecialTable(ResultSet rs) {
        try {
            
            this.dropExitingTable("WEEKSPECIAL");
            Statement statement = this.conn.createStatement();
            statement.executeUpdate("CREATE TABLE WEEKSPECIAL (TITLE VARCHAR(50), SPECIALPRICE FLOAT)");
            
            String title;
            float price;
            Float discount;
            
            while (rs.next()) {
                title = rs.getString("title");
                discount = rs.getFloat("discount");
                price = rs.getFloat("price");
                
                float specialPrice = price * (100 - discount) / 100;
                System.out.println("INSERT INTO WEEKSPECIAL VALUES ('"+ title + "', " + specialPrice + ")");
                statement.executeUpdate("INSERT INTO WEEKSPECIAL VALUES ('"+ title + "', " + specialPrice + ")");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropExitingTable(String table) {
        
        try {
            DatabaseMetaData metaData = this.conn.getMetaData();
            Statement statement = this.conn.createStatement();
            ResultSet rs = metaData.getTables(null, null, table, null);
            
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
                if (rs.getString("TABLE_NAME").equalsIgnoreCase(table)) {
                    statement.executeUpdate("DROP TABLE " + table);
                    break;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
