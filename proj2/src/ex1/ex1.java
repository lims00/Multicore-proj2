package ex1;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ex1 {
	public static void main(String[] args) {
		BlockingQueue<Nums> numqueue = new ArrayBlockingQueue<>(5);
		NumList numlist= new NumList(numqueue);
		for (int i=1;i<=10;i++) {
			Nums n = new Nums("num "+i,numlist);
			n.start();
		}
	}
	
	
}

class NumList{
	private BlockingQueue<Nums> numlist;
	public NumList(BlockingQueue<Nums> numlist) {
		this.numlist= numlist;
	}
	public void in(Nums n) throws InterruptedException{
		System.out.println(n.getName()+": trying to put in numlist");
		numlist.put(n);
		System.out.println(n.getName()+": just put");
	}
	public void out(Nums n) throws InterruptedException{
		System.out.println(n.getName()+"                           about to take out");
		numlist.take();
		System.out.println(n.getName()+"                           have been taken");
	}
}

class Nums extends Thread{
	private NumList queue;
	public Nums(String name,NumList queue) {
		this.queue=queue;
	}
	public void run() {
		while(true) {
			try {
					sleep((int)(Math.random()*1000));
					queue.in(this);
			}catch(InterruptedException e) {}
			try {
				sleep((int)(Math.random()*2000));
				queue.out(this);
			}catch(InterruptedException e) {}
		}
				
	}
}