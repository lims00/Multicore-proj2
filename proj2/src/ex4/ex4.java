package ex4;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ex4 extends Thread {
    private CyclicBarrier barrier;
    private String name;

    public ex4(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }

    public void run() {
        System.out.println(name + " is waiting at the barrier...");
        try {
            barrier.await(); // arrived at barrier and wait
            System.out.println(name + " has passed the barrier!");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numThreads = 5; // number of thread
        CyclicBarrier barrier = new CyclicBarrier(numThreads); 

        
        for (int i = 0; i < numThreads; i++) {
            String name = "Thread " + (i + 1);
            Thread thread = new ex4(barrier, name);
            thread.start();
        }
    }
}
