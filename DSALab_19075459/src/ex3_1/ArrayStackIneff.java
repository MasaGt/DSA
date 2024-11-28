/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3_1;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author wjh2989
 */
public class ArrayStackIneff<E> implements StackADT<E>{

    private E[] elements;
    private int numElems;
    private final int CAPACITY = 3;

    public ArrayStackIneff() {
        this.elements = (E[])new Object[CAPACITY];
        this.numElems = 0;      
    }

    public ArrayStackIneff(Collection<? extends E> c) {
        this();
        for (E element : c) {
            push(element);
        }
    }
    
    @Override
    public void push(E element) {
        if (numElems >= elements.length) {
            //System.out.println("\nExpand the capacity");
            expandCapacity();
        }
        
        shift();
        elements[0] = element;
        numElems++;
    }
    
    private void shift() {

        for (int i = numElems - 1; i >= 0; i--) {
            elements[i+1] = elements[i];
        }
        elements[0] = null;   
    }
    
    private void expandCapacity() {
        E[] newElements = (E[])(new Object[elements.length * 2]);
        
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        
        this.elements = newElements;
    }
    

    @Override
    public E pop() throws NoSuchElementException {
        if (numElems <= 0) {
            throw new NoSuchElementException();
        }
        
        E element = elements[0];
        unshift();
        numElems--;
        return element;
        
    }
    
    private void unshift() {
        
        for (int i = 1; i < numElems; i++) {
            elements[i - 1] = elements[i];
        }
        elements[numElems - 1] = null;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (numElems <= 0) {
            throw new NoSuchElementException();
        }
        
        return elements[0];
    }

    @Override
    public boolean isEmpty() {
        return numElems <= 0;
    }

    @Override
    public int size() {
        return numElems;
    }

    @Override
    public String toString() {
        String result = "[ ";
        for (int i = 0; i < numElems; i++) {
            result += elements[i];
            
            if (i < numElems - 1) {
                result += ", ";
            }
        }
        
        result += " ]";
        return result;
    }
    
    public static void main(String[] args) {
        
        ArrayStackIneff<String> badStack = new ArrayStackIneff<>();
//        Inefficient stack funtionality Test
        System.out.println("----------Inefficient stack funtionality Test----------");
        System.out.print("Push A");
        badStack.push("A");
        System.out.println(badStack);
        
        System.out.print("Push B");
        badStack.push("B");
        System.out.println(badStack);
        
        System.out.print("Push C");
        badStack.push("C");
        System.out.println(badStack);
        
        System.out.print("Pop one on the top");
        String rval = badStack.pop();
        System.out.print(badStack);
        System.out.println("    Returned value is " + rval);
        
        System.out.print("Push D");
        badStack.push("D");
        System.out.println(badStack);
        
        System.out.print("Push E");
        badStack.push("E");
        System.out.println(badStack);
        
        System.out.print("Push F");
        badStack.push("F");
        System.out.println(badStack);
        
        
        System.out.print("peek the element on the top: ");
        System.out.println(badStack.peek());
        
        System.out.print("Push G");
        badStack.push("G");
        System.out.println(badStack);
        
        System.out.print("Push H");
        badStack.push("H");
        System.out.println(badStack);
        
        System.out.println("----------performance comparison----------");
//        ArrayStackIneff<String> badStack = new ArrayStackIneff<>();
         badStack = new ArrayStackIneff<>();
        // performance comparison
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            badStack.push("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("badstack took: " + (end - start) + "ms");
        
        
        ArrayStack<String> goodStack = new ArrayStack<>();
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            goodStack.push("a");
        }
        
        end = System.currentTimeMillis();
        System.out.println("goodstack took: " + (end - start) + "ms");
        
        System.out.println("size of the good stack is " + goodStack.size());


    }
}
