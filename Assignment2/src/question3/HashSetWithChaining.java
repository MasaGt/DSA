/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question3;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author Masaomi
 */
public class HashSetWithChaining<E> implements Set<E>{
    
    private Node<E>[] hashTabale;
    private int numElems;
    private float loadFactor;
    private int capacity;
    
    public HashSetWithChaining(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be positive");
        }
        hashTabale = new Node[capacity];
        numElems = 0;
        loadFactor = 0.75f;
        this.capacity = capacity;
    }

    public HashSetWithChaining(int capacity, float loadFactor) {
        this(capacity);
        if (loadFactor <= 0f) {
            throw new IllegalArgumentException("load factor should be positive");
        }
        this.loadFactor = loadFactor;
    }
    
    /**
     * add an element to the hash table
     * @param e
     * @return true if the element successfully added. Otherwise, false
     */
    @Override
    public boolean add(E e) {
        
        //duplicate check
        if (contains(e)) {
            return false;
        }
        
        //hash table size check
        if (numElems >= (hashTabale.length*loadFactor)) {
            expandCapacity();
        }
        
        //calc index from the hash code of an element
        int index = calcIndex(e, hashTabale.length);
        
        //add an element by chainning
        Node newNode = new Node(e);
        newNode.next = hashTabale[index];
        hashTabale[index] = newNode;
        numElems++;
        return true;
    }
    
    /**
     * expand capacity and re-allocate elements by re-calculating hasCode
     */
    private void expandCapacity() {

        capacity = capacity * 2;
        Node<E>[] newHashTable = new Node[capacity];
        
        for (int i = 0; i < hashTabale.length; i++) {
            Node<E> target = hashTabale[i];
            while (target != null) {
                int index = calcIndex(target.element, newHashTable.length);
                Node<E> next = target.next;
                target.next = newHashTable[index];
                newHashTable[index] = target;
                target = next;
            }
        }
        hashTabale = newHashTable;
    }
    
    /**
     * add all the elements passed
     * @param c
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        
        boolean result = false;
        for (E element : c) {
            boolean isAdded = add(element);
            if (isAdded) {
                result = isAdded;
            }
        }
        return result;
    }
    
    
    /**
     * remove all the element from this collection
     */
    @Override
    public void clear() {
        
        Iterator itr = iterator();
        while (itr.hasNext()) {
            remove(itr.next());
        }
    }
    
    /**
     * Check if a passed element already exists in the hash table or not,
     * @param o element to be checked
     * @return true if the element already exists. Otherwise, false
     */
    @Override
    public boolean contains(Object o) {
        
        int index = calcIndex((E) o, hashTabale.length);
        Node<E> firstNode = hashTabale[index];
        boolean isFound = false;
        
        while (firstNode != null && !isFound) {
            
            if ((o == null && firstNode.element == null) || 
                    (firstNode.element != null && firstNode.element.equals(o))) {
                //the element already exists
                isFound = true;
            }
            firstNode = firstNode.next;
        }
        return isFound;
    }
    
    /**
     * check if this collection contains all the passed elements.
     * @param c
     * @return true if this collection contains al the elements that the specified collection has
     *          false if there is even one element in parameters that this collection does contain
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        
        boolean result = true;
        for (Object element : c) {
            boolean isContained = contains(element);
            if (!isContained) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }
    
    @Override
    public boolean isEmpty() {
        return numElems <= 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }
    
    /**
     * remove the passed element
     * @param o
     * @return true if the element successfully removed.
     */
    @Override
    public boolean remove(Object o) {
        
        int index = calcIndex((E) o, hashTabale.length);
        boolean isFound =false;
        Node<E> firstNode = hashTabale[index];
        
        if (firstNode != null) {
            if ((o == null && firstNode.element == null) ||
                    (firstNode.element != null && firstNode.element.equals(o))) {
                //special case for removing the first node
                isFound = true;
                hashTabale[index] = firstNode.next;
                //sever the link. The target node will be garbage-colltected
                firstNode.next = null;
                numElems--;
            } else {
                //follow the chain to find a node that holds same element
                Node<E> previous = firstNode;
                Node<E> current = firstNode.next;
                while (current != null && !isFound) {
                    if ((o == null && current.element == null)||
                            (current.element != null && current.element.equals(o))) {
                        //found an elemenet to remove
                        isFound = true;
                        //sever the link. The target node will be garbage-colltected
                        previous.next = current.next;
                        numElems--;
                    }
                    previous = current;
                    current = current.next;
                }
            }
        }
        
        return isFound;
    }
    
    /**
     * remove all the elements passed
     * @param c
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object element : c) {
            boolean isRemoved = remove(element);
            if (isRemoved) {
                result = isRemoved;
            }
        }
        
        return result;
    }
    
    /**
     * retain only passed elements, the other elements are removed.
     * @param c
     * @return true if this collection changed as a result of the call
     * -> return true even if there is one element removed from this collection
     * -> return false only if there is nothing removed from this collection
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        
        boolean isRemoved = false;
        
        if (c == null) {
            throw new IllegalArgumentException();
        }
        
        Iterator<E> itr = iterator();    
        while (itr.hasNext()) {
            E element = itr.next();
            if (!c.contains(element)) {
                remove(element);
                isRemoved = true;
            }
        }
        return isRemoved;
    }
    
    @Override
    public int size() {
        return numElems;
    }

    /**
     * returns an array containing all of the elements in this collection
     * @return 
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[numElems];
        
        Iterator<E> itr = iterator();
        int indexForArr = 0;
        while (itr.hasNext()) {
            E element = itr.next();
            arr[indexForArr++] = element;
        }
        
        return arr;
    }

    /**
     * returns an array containing all of the elements in this collection
     * also copy the elements in this collection to the passed array if its length is larger or quals to the size of this collection
     * @param <T>
     * @param a an array specifies the data type
     * @return an array containing all of the elements in this collection
     */
    @Override
    public <T> T[] toArray(T[] a) {
        
        if (a == null) {
            throw new IllegalArgumentException();
        }
        
        int returnArrSize = numElems;
        if (a.length > numElems) {
            returnArrSize = a.length;
        }
        
        //This throws cast exception
        //T[] returnArr = (T[]) new Object[returnArrSize];
        T[] returnArr = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), returnArrSize);
        
        if (a.length < numElems) {
            //passed array is smaller than this collection
            
            //set null to a (passed array)
            for (int i = 0; i < a.length; i++) {
                a[i] = null;
            }
            
            //set all the elements in this collection to the returnArray
            Iterator<E> itr = iterator();
            int index = 0;
            while (itr.hasNext()) {
                returnArr[index++] = (T) itr.next();
            }
            
        } else {
            //passed array is larger or equal to this collection
            Iterator<E> itr = iterator();
            for (int i = 0; i < returnArrSize; i++) {
               while (itr.hasNext()) {
                   E element = itr.next();
                   a[i] = (T) element;
                   returnArr[i++] = (T) element;
               }
               //fill empty slots with null
               if (i < returnArrSize) {
                   a[i] = null;
                   returnArr[i] = null;
               }
               
            }
        }
        
        return returnArr;
    }

    @Override
    public String toString() {
        
        String msg = "";
        for (int i = 0; i < hashTabale.length; i++) {
            msg += "Row" + i + ": ";
            Node<E> current = hashTabale[i];
            while (current != null) {
                msg += current.element;
                if (current.next != null) {
                    msg += "-->";
                }
                current = current.next;
            }
            msg += "\n";
        }
        
        return msg;
    }
    
    
    
    private class Node<E> {
        public E element;
        public Node next;

        public Node(E element) {
            this.element = element;
            next = null;
        }
    }
    
     private class LinkedIterator implements Iterator<E> {

         public Node<E> node;
         public int currentIndex;
         
        public LinkedIterator() {
            node = null;
            currentIndex = 0;
        }
         
        @Override
        public boolean hasNext() {
            boolean hasNext = false;
            if (node != null) {
                hasNext = true;
            } else {
                //search not null slot in hashTable
                for (; currentIndex < hashTabale.length; currentIndex++) {
                    node = hashTabale[currentIndex];
                    if (node != null) {
                        hasNext = true;
                        currentIndex++;
                        break;
                    }
                }
            }

            return hasNext;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            E element = node.element;
            node = node.next;
            return element;
        }
     }
    
     /**
      * calculate the index from the hash code of an element
      * @param elem element
      * @param tableSize hashTable size
      * @return index for the element in hashTable
      */
     private int calcIndex(E elem, int tableSize) {
         return elem != null ? Math.abs(elem.hashCode() % tableSize) : 0;
     }
    
}