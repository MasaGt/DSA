/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5_1;

/**
 *
 * @author Masaomi
 */
public class HashTable<E> {
    private final int LIMIT = 5;
    private Node<E>[] table;
    private int numElem;

    public HashTable() {
        table = new Node[LIMIT];
        numElem = 0;
    }
    
    public void add(E elem) {
        //table size check (if the table is filled 75%, then expand the capacity)
        if (numElem >= table.length * 0.75f) {
            //expand and re-calc index
            expandCapacity();
        }
        int index = calcIndex(elem, table.length);
        //chaining
        Node newNode = new Node(elem);
        newNode.next = table[index];
        table[index] = newNode;
        numElem++;
    }
    
    public boolean remove(E elem) {
        int index = calcIndex(elem, table.length);
        boolean found =false;
        Node firstNode = table[index];
        if (firstNode != null) {
            //special case for removing the first node.
            if ((firstNode.element == null && elem == null) ||
                (firstNode.element != null && firstNode.element.equals(elem))) {
                found = true;
                table[index] = firstNode.next;
                numElem--;
            } else {
                //iterate through the link to find the target to remove
                Node prevNode = firstNode;
                Node taregt = firstNode.next;
                
                while (taregt != null && !found) {
                    if ((taregt.element == null && elem == null) || (taregt.element != null && taregt.element.equals(elem))) {
                        found = true;
                        prevNode.next = taregt.next;
                        numElem--;
                    }
                    
                    prevNode = taregt;
                    taregt = taregt.next;
                }
            }
        }
        return found;
    }
    
    public boolean contains(E elem) {
        int index = calcIndex(elem, table.length);
        Node target = table[index];
        boolean found = false;
        while (target != null && !found) {
            if (target.element.equals(elem)) {
                found = true;
            }
            target = target.next;
        }
        
        return found;
    }
    
    private void expandCapacity() {
        System.out.println("EXPAND CAPACITY");
        Node<E>[] newTable = new Node[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            Node<E> target = table[i];
            while (target != null) {
                int index = calcIndex(target.element, newTable.length);
                Node nextNode = target.next;
                target.next = newTable[index];
                newTable[index] = target;
                target = nextNode;
            }
        }
        table = newTable;
    }
    
    private int calcIndex(E elem, int tableSize) {
        return Math.abs(elem.hashCode() % tableSize);
    }

    @Override
    public String toString() {
        String msg = "HashTable: \n";
        
        for (int i = 0; i < table.length; i++) {
            msg += "Row" + i + ":";
            Node<E> target = table[i];
            while (target != null) {
                msg += " " + target.element;
                target = target.next;
            }
            msg += "\n";
        }
        return msg;
    }
    
    
    
    private class Node<E> {
        private E element;
        private Node next;
        
        public Node(E element)
      {  this.element = element;
         next = null;
      }
    } 
    
    public static void main(String[] args) {
        Student s1 = new Student("Alan", 20, "student1");
        Student s2 = new Student("Bod", 25, "student2");
        Student s3 = new Student("Dylan", 30, "student3");
        Student s4 = new Student("Andy", 18, "student4");
        Student s5 = new Student("Calc", 23, "student5");

        HashTable<Student> hashTable = new HashTable<>();
        //test addition
        System.out.println("----------add test----------");
        hashTable.add(s1);
        System.out.println(hashTable);
        hashTable.add(s2);
        System.out.println(hashTable);
        hashTable.add(s3);
        System.out.println(hashTable);
        hashTable.add(s4);
        System.out.println(hashTable);
        hashTable.add(s5);
        System.out.println(hashTable);
        
        Student s6 = new Student("Greg", 40, "student6");
        hashTable.add(s6);
        System.out.println(hashTable);
        
        
        //test remove
        System.out.println("----------remove test----------");
        System.out.println("----------Remove Bob----------");
        hashTable.remove(s2);
        System.out.println(hashTable);
        
        //test contains
        System.out.println("----------contains test----------");
        System.out.println("HashTable conatins Alan: " + hashTable.contains(s1));
        Student s7 = new Student("Cris", 32, "Student7");
        System.out.println("HashTable conatins Cris: " + hashTable.contains(s7));
        
    }
 }
