/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author wjh2989
 */
public class ArrayQueue<E> implements QueueADT<E>{

    private E[] elems;
    private int numElem;
    private int head;
    private int tail;
    private int CAPACITY = 3;

    public ArrayQueue() {
        this.numElem = 0;
        this.head = 0;
        this.tail = 0;
        this.elems = (E[])(new Object[CAPACITY]);
    }

    public ArrayQueue(Collection<? extends E> c) {
        this();
        
        for (E elem : c) {
            enqueue(elem);
        }
    }
            
    @Override
    public void enqueue(E element) {
        
        if (numElem >= elems.length) {
            expandCapacity();
        }
        
        if (tail >= elems.length) {
            tail = 0;
        }
        elems[tail++] = element;
        numElem++;        
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        
        if (numElem <= 0) {
            throw new NoSuchElementException();
        }
        
        if (head >= elems.length) {
            head = 0;
        }
        
        E element = elems[head];
        elems[head++] = null;
        numElem--;
        return element;
    }
    
    private void expandCapacity() {
        E[] newArray = (E[]) new Object[elems.length * 2];
        
        for (int i = 0; i < elems.length; i++) {
            if (head >= elems.length) {
                head = 0;
            }
            
            newArray[i] = elems[head++];
        }
        
        head = 0;
        tail = elems.length;
        this.elems = newArray;
    }

    @Override
    public E first() throws NoSuchElementException {
        if (numElem <= 0) {
            throw new NoSuchElementException();
        }
        
        return elems[head];
        
    }

    @Override
    public boolean isEmpty() {
        return numElem <= 0;
    }

    @Override
    public int size() {
        return this.numElem;
    }

    @Override
    public String toString() {
        String result = "[ ";
        
        for (int i = 0; i < this.elems.length; i++) {
            result += this.elems[i];
            
            if (i < this.elems.length-1) {
                result += ", ";   
            }
        }
        
        result += " ]";
        
        return result;
    }
    
    public static void main(String[] args) {
        
        ArrayQueue<String> queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        
        System.out.println(queue);
        
        System.out.print("deque");
        queue.dequeue();
        System.out.println(queue);
         
        System.out.print("deque");
        queue.dequeue();
        System.out.println(queue);
        
        System.out.print("check wrapping enqueue");
        queue.enqueue("D");
        System.out.println(queue);
        
        System.out.print("deque");
        queue.dequeue();
        System.out.println(queue);
        
        System.out.print("check wraping deque");
        queue.dequeue();
        System.out.println(queue);
        
        System.out.print("enqueue");
        queue.enqueue("E");
        System.out.println(queue);
        
        
        System.out.print("enqueue");
        queue.enqueue("F");
        System.out.println(queue);
        
        System.out.print("enqueue");
        queue.enqueue("G");
        System.out.println(queue);
        
        System.out.print("check expansion of the queue capacity");
        queue.enqueue("H");
        System.out.println(queue);
        
        //create a new queue based on an exiting array. I comment out becase it worked properly.
//        Collection<Integer> c = new ArrayList<>();
//        c.add(1);
//        c.add(10);
//        c.add(100);
//        
//        ArrayQueue<Integer> newQueue = new ArrayQueue<>(c);
//        System.out.println(newQueue);
//        
//        System.out.print("dequeue");
//        newQueue.dequeue();
//        System.out.println(newQueue);
//        
//        System.out.print("enqueue");
//        newQueue.enqueue(20);
//        System.out.println(newQueue);
//        
//        System.out.print("enqueue");
//        newQueue.enqueue(200);
//        System.out.println(newQueue);
        
    }
    
}
