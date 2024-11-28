/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9_ex2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author Masaomi
 */
public class Enrollment {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Student> map = new LinkedHashMap<>();
        
        map.put("00000001", new Student("Alan", 20, "student1"));
        map.put("00000002", new Student("Bod", 25, "student2"));
        map.put("00000003", new Student("Dylan", 30, "student3"));
        map.put("00000004", new Student("Andy", 18, "student4"));
        map.put("00000005", new Student("Calc", 23, "student5"));
        map.put("00000006", new Student("Greg", 40, "student6"));
        map.put("00000007", new Student("Cris", 32, "Student7"));
        
        String input = "";
        do {            
            System.out.println("Enter studnetID");
            input = scan.nextLine();
            
            if (map.containsKey(input)) {
                Student student = map.get(input);
                System.out.println(student);
            } else if (!("quit".equalsIgnoreCase(input))) {
                System.out.println("No such a student");
            }
            
        } while (!("quit".equalsIgnoreCase(input)));
        
        for (Entry<String, Student> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
