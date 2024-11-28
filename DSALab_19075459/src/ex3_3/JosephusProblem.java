/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author wjh2989
 */
public class JosephusProblem {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the number of soliders");
        
        int num = sc.nextInt();
        JosephusProblem ex = new JosephusProblem();
        List<Soldier> soldiers = ex.populate(num);
        System.out.println(soldiers);
          
        System.out.println("Type -th solder to remove");
        
        int index = sc.nextInt();
        
        List<Soldier> result = solveProblem(soldiers, index);
        System.out.println("permutation: " + result);
        
        sc.close();
    }
    
    private static<E> List<E> solveProblem (List<E> elements, int index) {
        
        Queue<E> queue = new LinkedList<E>();
        for (E soldier : elements) {
            queue.offer(soldier);            
        }
        
        //permutation list
        List<E> result = new ArrayList<>();
        
        int counter = 1;
        while (queue.size() != 0) {
            
            if (counter % index == 0) {
                result.add(queue.poll());
            } else {
                queue.offer(queue.poll());
            }
            counter++;
            System.out.println("-----" + queue + "-----");
        }
        
        return result;
    }
                  
    private List populate(int num){
        
        List<Soldier> soldiers = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            soldiers.add(new Soldier(i + 1));
        }
        return soldiers;
    }
    
    private class Soldier{
        private int num;

        public Soldier(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Soldier: " + num;
        }
        
    }
    
}
