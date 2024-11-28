package Task05_1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Counter implements Runnable{

    int num;

    public static void main(String[] args) {
//        Counter count = new Counter(1);
//        count.printNum();

        //-------task1-------
//        Runnable odd = new Counter(1);
//        Thread th1 = new Thread(odd);
//        th1.start();
        //-------task1-------

        //-------task2-------
        Runnable even = new Counter(0);
        Runnable odd = new Counter(1);
        Thread th1 = new Thread(even);
        Thread th2 = new Thread(odd);
        
        th1.start();
        th2.start();
        //-------task2-------
        
    }

    public Counter(int i) {
        this.num = i;
    }

    public void printNum() {
        for (int j = this.num; j <= 10; j += 2) {
            System.out.print(j + " ");
            try {
                //task3
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Counter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void run() {
        printNum();
    }
    
    

}
