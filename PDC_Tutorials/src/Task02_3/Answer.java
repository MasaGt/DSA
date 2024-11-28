/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task02_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class Answer {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> records = readRecords();
        String name = null;
        Integer score = 0;
        
        while (true) {
            System.out.println(records);
            System.out.println("Input your name");
            name = checkInput(sc, records);
            boolean isValid = false;
            
            while (!isValid) {
                try {
                    System.out.println("Enter your score");
                    score = Integer.parseInt(checkInput(sc, records));
                    isValid = true;
                } catch (NumberFormatException e) {
                    Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
            if (records.containsKey(name)) {
                System.out.println("you wanna overrwrite?");
                if ("Y".equalsIgnoreCase(checkInput(sc, records))) {
                    records.replace(name, score);
                }
            } else {
                records.put(name, score);
            }
        }
    }
    
    public static HashMap<String, Integer> readRecords() {
        
        HashMap<String, Integer> records = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./resources/T02_scores.txt"));
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split(" ");
                records.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return records;
    }
    
    public static void writeToFile(HashMap<String, Integer> records) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("./resources/T02_scores.txt");
            
            for (String key : records.keySet()) {
                pw.println(key + " " + records.get(key));
            }
        } catch (IOException ex) {
            Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    public static String checkInput(Scanner sc, HashMap<String, Integer> records) {
        String input = sc.nextLine();
        
        if ("x".equalsIgnoreCase(input.trim())) {
            writeToFile(records);
            System.exit(0);
        }
        return input;
    }
    
}
