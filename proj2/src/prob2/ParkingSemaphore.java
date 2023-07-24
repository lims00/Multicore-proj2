package prob2;
import java.util.concurrent.Semaphore;

class ParkingGarage{
	private Semaphore s;
	private int places;
	public ParkingGarage(int places) {
	    this.s = new Semaphore(places,true);
	  }
	public void enter(Car car) throws InterruptedException { // enter parking garage
		System.out.println(car.getName()+": trying to enter");
		s.acquire();
		System.out.println(car.getName()+": just entered");
	  }
	public void leave(Car car) throws InterruptedException { // leave parking garage
		System.out.println(car.getName()+":                                     about to leave");
		s.release();
		System.out.println(car.getName()+":                                     have been left");
		
	  }
	  public int getPlaces()
	  {
	    return s.availablePermits();
	  }
	}


	class Car extends Thread {
	  private ParkingGarage parkingGarage;
	  public Car(String name, ParkingGarage p) {
	    super(name);
	    this.parkingGarage = p;
	    start();
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


	public class ParkingSemaphore {
	  public static void main(String[] args){
	    ParkingGarage parkingGarage = new ParkingGarage(7);
	    for (int i=1; i<= 10; i++) {
	      Car c = new Car("Car "+i, parkingGarage);
	    }
	  }
	}


