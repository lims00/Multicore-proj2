package ex2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ex2{
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private static int value;

    static class ReaderThread extends Thread {
        private ReadWriteLock lock;
        private int id;

        public ReaderThread(ReadWriteLock lock, int id) {
            this.lock = lock;
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                readLock();
                try {
                    System.out.println("Reader " + id + " read value: " + value);}
                    
                finally {
                    unlockReadLock();
                }
                try{Thread.sleep(1000); // wait 1 sec
                } catch(InterruptedException e) {}
            }
        }
        
        // Read Lock 
        private void readLock() {
            lock.readLock().lock();
        }
        
        // Read unLock 
        private void unlockReadLock() {
            lock.readLock().unlock();
        }
    }

    
    static class WriterThread extends Thread {
        private ReadWriteLock lock;
        private int id;

        public WriterThread(ReadWriteLock lock, int id) {
            this.lock = lock;
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                writeLock();
                try {
                    value++; // increase value
                    System.out.println("Writer " + id + " write value: " + value);
                     // 1 sec wait
                }finally {
                    unlockWriteLock();
                }
                try {
                	Thread.sleep(1000);
                }catch (InterruptedException e) {} 
            }
        }
        
        // get Write Lock 
        private void writeLock() {
            lock.writeLock().lock();
        }
        
        // Write unLock 
        private void unlockWriteLock() {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
    	ex2 example = new ex2();

       //readerThread
        for (int i = 0; i < 5; i++) {
            ReaderThread readerThread = new ReaderThread(example.lock, i);
            readerThread.start();
        }
        //writerThread
        for (int i = 0; i < 3; i++) {
            WriterThread writerThread = new WriterThread(example.lock, i);
            writerThread.start();
        }
    }
}
