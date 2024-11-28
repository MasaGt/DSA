/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4_4;

/**
 *
 * @author Masaomi
 */
public class Task<E extends Comparable> implements Comparable<Task>{

    //smaller priority means more important task
    private int priority;
    private E element;
    private long timestamp;

    public Task(int priority, E element) {
        this.priority = priority;
        this.element = element;
        this.timestamp = System.nanoTime();
    }

    public Task(E element) {
        this(1, element);
    }
            
    @Override
    public int compareTo(Task o) {
        int result = getPriority() - o.getPriority();
        if (result == 0) {
            result = (int) (timestamp - o.timestamp);
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + getElement() + ", priprity:" + getPriority() + "]";
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return the element
     */
    public E getElement() {
        return element;
    }
    
}
