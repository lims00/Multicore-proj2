package prob1;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ParkingGarage {
	
    private BlockingQueue<Car> parkingQueue;
   
    public ParkingGarage(BlockingQueue<Car> parkingQueue) {
		// TODO Auto-generated constructor stub
        this.parkingQueue = parkingQueue;
	}
	public void enter(Car car) throws InterruptedException{ // enter parking garage
		System.out.println(car.getName()+": trying to enter");
		parkingQueue.put(car);
		
		System.out.println(car.getName()+": just entered");
	  }
	public void leave(Car car) throws InterruptedException { // leave parking garage
		
		System.out.println(car.getName()+":                                     about to leave");
		parkingQueue.take();
		System.out.println(car.getName()+":                                     have been left");
	}
	public int getPlaces() {
		return parkingQueue.remainingCapacity();
	}
	  
}


class Car extends Thread {
	  private ParkingGarage parkingGarage;
	  public Car(String name, ParkingGarage p) {
	    super(name);
	    this.parkingGarage = p;
	  }

	  public void run() {
	    while (true) {
	      try {
	        sleep((int)(Math.random() * 10000)); // drive before parking
	        parkingGarage.enter(this);
	        
	      } catch (InterruptedException e) {}
	      
	      try {
	        sleep((int)(Math.random() * 20000)); // stay within the parking garage
	        parkingGarage.leave(this);
	      } catch (InterruptedException e) {}
	      

	    }
	  }

	}


public class ParkingBlockingQueue {
	public static void main(String[] args){
		  BlockingQueue<Car> parkingQueue = new ArrayBlockingQueue<>(7);
		  
		  ParkingGarage parkingGarage = new ParkingGarage(parkingQueue);
		  for (int i=1; i<= 10; i++) {
			  Car c = new Car("Car "+i, parkingGarage);
			  c.start();
	    }
	  }
}
