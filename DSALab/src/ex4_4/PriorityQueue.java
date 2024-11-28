/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4_4;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masaomi
 */
public class PriorityQueue implements QueueADT<Task<?>>{
    
    //underlying data (PriorityQueue just decorates ArrayHeap)
    private final ArrayHeap<Task<?>> heap;

    public PriorityQueue() {
        //use Task.compareTo 's comparison
        this.heap = new ArrayHeap<>();
        //use Comparator's comparison
//        this.heap = new ArrayHeap<>(new PriorityComparator());
    }
    
    @Override
    public void enqueue(Task<?> element) {
        heap.add(element);
    }

    @Override
    public Task<?> dequeue() throws NoSuchElementException {
        return heap.removeMin();
    }

    @Override
    public Task<?> first() throws NoSuchElementException {
        return heap.getMin();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }
    
    //inner comparator class for priorityQueue
    private class PriorityComparator implements Comparator<Task>{

        @Override
        public int compare(Task o1, Task o2) {
            //smaller priority comes to the top
            int result = o1.getPriority() - o2.getPriority();
            //bigger priority comes to the top
//            int result = o2.getPriority() - o1.getPriority();
            if (result == 0) {
                //if each priority equals, compare their contents
                result = o1.getElement().compareTo(o2.getElement());
            }
            
            return result;
        }
    }
    
    public static void main(String[] args) {
        //just for checking how they work differently
//        QueueADT<Task<?>> queue = new LinkedQueue<>();
        QueueADT<Task<?>> queue = new PriorityQueue();
        //data set for testing Task.compareTo
        queue.enqueue(new Task<>(8, "Task8"));
        queue.enqueue(new Task<>(4, "Task4"));
        queue.enqueue(new Task<>(7, "Task7"));
        queue.enqueue(new Task<>(0, "Task0_1"));
        queue.enqueue(new Task<>(5, "Task5"));
        queue.enqueue(new Task<>(9, "Task9_1"));
        queue.enqueue(new Task<>(2, "Task2"));
        queue.enqueue(new Task<>(3, "Task3_1"));
        queue.enqueue(new Task<>(6, "Task6_1"));
        queue.enqueue(new Task<>(10, "Task10"));
        queue.enqueue(new Task<>(0, "Task0_2"));
        queue.enqueue(new Task<>(1, "Task1"));
        queue.enqueue(new Task<>(9, "Task9_2"));
        queue.enqueue(new Task<>(3, "Task3_2"));
        queue.enqueue(new Task<>(6, "Task6_2"));

        //data set for testing PriorityComparator
//        queue.enqueue(new Task<>(4, "TaskN"));
//        queue.enqueue(new Task<>(7, "TaskM"));
//        queue.enqueue(new Task<>(0, "TaskL"));
//        queue.enqueue(new Task<>(5, "TaskK"));
//        queue.enqueue(new Task<>(9, "TaskJ"));
//        queue.enqueue(new Task<>(2, "TaskI"));
//        queue.enqueue(new Task<>(3, "TaskH"));
//        queue.enqueue(new Task<>(6, "TaskG"));
//        queue.enqueue(new Task<>(10, "TaskF"));
//        queue.enqueue(new Task<>(0, "TaskE"));
//        queue.enqueue(new Task<>(1, "TaskD"));
//        queue.enqueue(new Task<>(9, "TaskC"));
//        queue.enqueue(new Task<>(3, "TaskB"));
//        queue.enqueue(new Task<>(6, "TaskA"));
        
        //show the contents of the queue
        while (!queue.isEmpty()) {
            System.out.println("Processing >>" + queue.dequeue());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PriorityQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
