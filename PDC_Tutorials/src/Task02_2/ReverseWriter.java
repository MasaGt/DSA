/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task02_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class ReverseWriter {

    public static void main(String[] args) {
            BufferedReader br = null;        
            PrintWriter pw = null;
        try {
            br = new BufferedReader(new FileReader("./resources/T02_input.txt"));
            pw = new PrintWriter("./resources/T02_output.txt");
            StringBuilder sb = new StringBuilder();
            String line = "";

            //Reverse the letters' order in each line
            while ((line = br.readLine()) != null) {
                String reversed = "";
                for (int i = line.length() - 1; i >= 0; i-- ) {
                    if (Character.isLetter(line.charAt(i))) {
                        reversed += Character.toUpperCase(line.charAt(i));
                    }
                }
                pw.println(reversed);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReverseWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReverseWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReverseWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (pw != null) {
                pw.close();
            }
        }
        
    }
    
}
