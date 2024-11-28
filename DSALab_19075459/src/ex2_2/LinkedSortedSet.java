/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2_2;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author wjh2989
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E>{
    
   @Override
   public boolean add(E o) {
       
       Node<E> newNode = new Node<E>(o);
       
       if (firstNode == null || o.compareTo(firstNode.element) < 0) {
           //insert the first node
           newNode.next = firstNode;
           firstNode = newNode;
           numElements++;
           return true;
       } else {
           
           Node<E> current = firstNode;
           
           //walk through the elements          
           while(current.next != null){
               if (o.compareTo(current.next.element) < 0) {
                   //if o is smaller than the node, o goes before the node.
                   newNode.next = current.next;
                   current.next = newNode;
                   numElements++;
                   return true;
               } else if (o.compareTo(current.next.element) == 0) {
                   //If there is the same element. not add o
                   return false;
               } else {
                   current = current.next;
               }
           }
           
           current.next = newNode;
           numElements++;
           //mock true
           return true;
        }
    }
   
    public static void main(String[] args) {
        
        Set<String> students = new LinkedSortedSet<>();
        students.add("Sam");
        students.add("Tom");
        students.add("Aki");
        students.add("AAA");
        students.add("Bob");
        students.add("BCAA");
        students.add("Sai");
        //duplicate element
        students.add("BCAA");
        
        Iterator<String> it1 = students.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
        
        System.out.println("size of the set is " + students.size() + "\n");
        
        
        Set<String> fruits = new LinkedSortedSet<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Berry");
        
        Iterator<String> it2 = fruits.iterator();
        while(it2.hasNext()) {
            System.out.println(it2.next());
        }
        System.out.println("size of the set fruits " + fruits.size() + "\n");
        
        Set<Integer> numbers = new LinkedSortedSet<>();
        numbers.add(1);
        numbers.add(10);
        numbers.add(2);
        numbers.add(23);
        numbers.add(0);
        //duplicate data
        numbers.add(1);
        //duplicate data
        numbers.add(10);
        numbers.add(11);
        
        Iterator<Integer> it3 = numbers.iterator();
        while(it3.hasNext()) {
            System.out.println(it3.next());
        }
        
        System.out.println("The size of the set \"numbers\" is " + numbers.size());
    }
}
