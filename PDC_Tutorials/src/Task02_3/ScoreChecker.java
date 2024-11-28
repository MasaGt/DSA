/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task02_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class ScoreChecker {
    
    public static void main(String[] args) {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("./resources/T02_scores.txt"));
            Scanner scanner = new Scanner(System.in);
            
            //Why LinkedHasMap? > to keep the order of students
            Map<String, Integer> studentMap = new LinkedHashMap();
            String line = "";
            String input = "";
                    
            while (!("x".equals(input))) {
                //The program loads the existing marks from scores.txt without overwriting the content in the file.
                while ((line = br.readLine()) != null) {
                    String[] keyValue = line.split(" ");
                    studentMap.put(keyValue[0], Integer.parseInt(keyValue[1]));
                }
                System.out.println(studentMap);
                //The program prompts the user to input a student name (first name only) and his/her exam mark.
                System.out.println("Input your first name.");
                input = scanner.nextLine();
                
                System.out.println("Input your mark.");
                Integer mark = Integer.parseInt(scanner.nextLine());
                
                //If the record is existing already, the program checks whether the user wants to overwrite the record.
                if (studentMap.containsKey(input)) {
                    System.out.println("You wanna overwrite your mark? y:yes, n:no");
                    if ("y".equals(scanner.nextLine())) {
                        studentMap.replace(input, mark);
                        writeMark(studentMap);
                    }
                } else {
                    studentMap.put(input, mark);
                    writeMark(studentMap);
                }
                
                System.out.println("Press \"x\" to quit or Press enter to continue");
                input = scanner.nextLine();
            }
            
            scanner.close();
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScoreChecker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScoreChecker.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException ex) {
            Logger.getLogger(ScoreChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void writeMark(Map<String, Integer> studentMap) throws IOException {
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/T02_scores.txt"));
        
        for (String key : studentMap.keySet()) {
            bw.write(key + " " + studentMap.get(key));
            bw.newLine();
        }
        
        bw.close();
    }
}
