package nz.ac.aut.pdc.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Weihua
 * @Note the program selects a PDF parser and convert a PDF file to plain text.
 */
public class PDFParserTest {

    public static void main(String[] args) {
        File file = new File("example.pdf");
        try {
            parsePDF(file);
        } catch (IOException | TikaException | SAXException ex) {
            Logger.getLogger(PDFParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void parsePDF(File file) throws IOException, TikaException, SAXException {
        FileInputStream inputstream = new FileInputStream(file);
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        //getting the content of the document
        System.out.println("Contents of the PDF :" + handler.toString());

        //getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }
    }
}
