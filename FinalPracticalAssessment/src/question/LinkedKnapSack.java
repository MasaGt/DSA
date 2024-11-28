/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Masaomi
 */
public class LinkedKnapSack<E> implements KnapSack<E>{

    private int maxNum;
    private int totalItem;
    private int totalType;
    private Node<E> firstNode;

    public LinkedKnapSack() {
        this(20);
    }
    
    public LinkedKnapSack(int maxNum) {
        this.maxNum = maxNum;
        totalItem = 0;
        totalType = 0;
        firstNode = null;
    }
    
    
    /**
     * add a passed item with a specified item amount
     * @param element
     * @param amount
     * @return 
     */
    @Override
    public boolean add(E element, int amount) {
        if ((totalItem + amount) > maxNum) {
            return false;
        }
        
        for (int i = 0; i < amount; i++) {
            add(element);
        }
        
        return true;
    }

    /**
     * add one passed item
     * @param element
     * @return 
     */
    @Override
    public boolean add(E element) {
        
        if (totalItem >= maxNum) {
            return false;
        }
        
        if (!contains(element)) {
            //add an item to the existing linked list
            Node<E> newNode = new Node<>(element);
            newNode.next = firstNode;
            firstNode = newNode;
            newNode.num++;
            totalType++;
        } else {
            //get the existing item from the linked list
            Node<E> current = firstNode;
            boolean isFound = false;
            while (current != null && !isFound) {
                if (current.item.equals(element)) {
                    isFound = true;
                    current.num++;
                } else {
                    current = current.next;
                }
            }
        }
        
        totalItem++;
        return true;
    }

    /**
     * remove all the passed items in KnapSack
     * @param element
     * @return 
     */
    @Override
    public boolean removeAll(E element) {
        if (firstNode == null || !contains(element)) {
            return false;
        }
        
        //get total num of an passed item;
        Node<E> current = firstNode;
        boolean isFound = false;
        int numRemove = 0;
        while (current != null && !isFound) {
            if (current.item.equals(element)) {
                isFound = true;
                numRemove = current.num;
            } else {
                current = current.next;
            }
        }
        
        remove(element, numRemove);
        return true;
    }

    /**
     * reduce item count by a passed amount
     * @param element
     * @param amount
     * @return 
     */
    @Override
    public boolean remove(E element, int amount) {
        if (firstNode == null || !contains(element)) {
            return false;
        }
        
        boolean isFound = false;
        if ((firstNode.item == null && element == null) || 
                (firstNode.item != null && firstNode.item.equals(element))) {
            //there is a item to be removed at front
            isFound = true;
            if ((firstNode.num - amount) <= 0) {
                this.totalItem = totalItem - firstNode.num;
                this.totalType--;
                firstNode = firstNode.next;
            } else {
                firstNode.num = firstNode.num - amount;
                this.totalItem -= amount;
            }
            
        } else {
            //get an item to be removed
            Node<E> previous = firstNode;
            Node<E> current = firstNode.next;
            while (current != null && !isFound) {
                if ((current.item == null && element == null) ||
                        (current.item != null && current.item.equals(element))) {
                    isFound = true;
                    
                    if (current.num - amount <= 0) {
                        this.totalItem = this.totalItem - current.num;
                        this.totalType--;
                        previous.next = current.next;
                        
                    } else {
                        current.num = current.num - amount;
                        this.totalItem -= amount;
                    }
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
        
        return true;
    }

    /**
     * check if the linked list contains an element which has the same element as the passed element
     * @param element
     * @return 
     */
    @Override
    public boolean contains(E element) {
        Iterator iteator = iterator();
        boolean isFound = false;
        while (!isFound && iteator.hasNext()) {
            E elem = (E) iteator.next();
            if (element.equals(elem)) {
                isFound = true;
            }
        }
        return isFound;
    }

    /**
     * get an random item with a random number.
     * not remove, just get.
     * @return 
     */
    @Override
    public E grab() {
        
        if (firstNode == null) {
            throw new NoSuchElementException("There is no item in KnapSack");
        }
        
        Random rand = new Random();
        int randomIndex = rand.nextInt(totalType);
        Node<E> previous = firstNode;
        Node<E> current = previous.next;
        //choose an item to grab
        for (int i = 0; i < randomIndex; i++) {
            previous = previous.next;
        }
        
        return previous.item;
    }

    @Override
    public int numberUniqueItems() {
        return totalType;
    }

    @Override
    public int currentQuantity() {
        return totalItem;
    }

    @Override
    public String toString() {
        String output = "[";
        
        Node<E> current = firstNode;
        
        while (current != null) {
            output += current.item + "=" + current.num;
            if (current.next != null) {
                output += ", ";
            }
            current = current.next;
        }
        return output + "]\n";
    }
    
    private Iterator<E> iterator() {
        return new LinkedIterator<E>(firstNode);
    }
    
    /**
     * Node class representing each item in KnapSack.
     * @param <E> 
     */
    private class Node<E> {
        public E item;
        public int num;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
            num = 0;
            this.next = null;
        }
        
        public Node(E item, int num) {
            this.item = item;
            this.num = num;
            this.next = null;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof Node) {
                if (this.item.equals(((Node) obj).item)) {
                    result = true;
                }
            } 
            return result;
        }
    }
    
    // inner class which represents an iterator for a singly-linked list
    private class LinkedIterator<E> implements Iterator<E> {

        private Node<E> nextNode;

        public LinkedIterator(Node<E> firstNode) {
            //set the first node
            nextNode = firstNode;
        }
        
        /**
         * returns if current element has the next nod
         * @return 
         */
        @Override
        public boolean hasNext() {
            return (nextNode!=null);
        }

        /**
         * return the element of the next node.
         * @return 
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = nextNode.item;
            nextNode = nextNode.next;
            return element;
        }
        
    }
    
}
