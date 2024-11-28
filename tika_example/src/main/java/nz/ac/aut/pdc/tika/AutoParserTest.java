package nz.ac.aut.pdc.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Weihua
 * @Note This program can select a auto parser and convert a
 * (word,excel,PowerPoint,PDF,etc.) document file to plain text
 */
public class AutoParserTest {

    public static void main(String[] args) {
        File file = new File("example.docx");
        try {
            String content = autoParse(file);
            System.out.println(content);
        } catch (IOException | TikaException | SAXException ex) {
            Logger.getLogger(PDFParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String autoParse(File file) throws FileNotFoundException, IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        /**
         * The try-with-resources statement is a try statement that declares one
         * or more resources. A resource is an object that must be closed after
         * the program is finished with it. The try-with-resources statement
         * ensures that each resource is closed at the end of the statement.
         *
         */
        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }
}
