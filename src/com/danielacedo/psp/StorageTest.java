package com.danielacedo.psp;

/**
 * Test execution of the simulation
 * @author Daniel Acedo Calderón
 *
 */
public class StorageTest {

	public static void main(String[] args) {
		Storage storage = new Storage();
		Withdrawal withdrawal = new Withdrawal(storage);
		Shipment shipment = new Shipment(storage);
		
		System.out.println("Simulation START");
		System.out.println("------------------");
		
		withdrawal.start();
		shipment.start();
		
		try {
			withdrawal.join();
			shipment.join();
		} catch (InterruptedException e) {
			System.err.println("Error during the execution of the simulation");
		}
		
		System.out.println("\n------------------");
		System.out.println("Simulation TERMINATED");
	}

}
