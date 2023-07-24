package ex3;

import java.util.concurrent.atomic.AtomicInteger;

public class ex3 extends Thread {
    private static AtomicInteger counter = new AtomicInteger();
    private int threadnum;
    public ex3(int threadnum) {
    	this.threadnum=threadnum;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int oldValue = counter.get(); // current value
            counter.set(oldValue + 1); // add 1 and set
            
            int newValue = counter.get(); // get value after add 1
            
            int addedValue = counter.getAndAdd(1); // add 1 and get value before adding
           
            int updatedValue = counter.addAndGet(1); // add 1 and get value after adding
            
            System.out.println("Thread " + threadnum + ": " +
                    "oldValue=" + oldValue +
                    ", newValue=" + newValue +
                    ", addedValue=" + addedValue +
                    ", updatedValue=" + updatedValue);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // #thread is 3
        ex3[] threads = new ex3[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new ex3(i);
            threads[i].start();
        }

        // waiting all thread terminate
        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }

        // print result
        System.out.println("Result: " + counter.get());
    }
}
