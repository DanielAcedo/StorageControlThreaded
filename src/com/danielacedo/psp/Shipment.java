package com.danielacedo.psp;

import java.util.Random;

/**
 * Class representing a shipment of stock arriving to the storage
 * @author Daniel Acedo Calderón
 *
 */
public class Shipment extends Thread{
	public static final int SHIPMENT_PERIOD = 800;			//How often the shipment will occur
	public static final int MIN_SHIPMENT_QUANTITY = 400;	//Minimum amount of stock shipped
	public static final int MAX_SHIPMENT_QUANTITY = 1000;	//Maximum amount of stock shipped
	
	Storage storage;										//Shared object
	
	public Shipment(Storage storage){
		this.storage = storage;
	}
	
	@Override
	public void run(){
		Random rnd = new Random();
		
		//Keep running as long as there are no errors
		while(!storage.getError()){
			try {
				sleep(SHIPMENT_PERIOD);	//Wait the defined period
				storage.receiveStock(MIN_SHIPMENT_QUANTITY + rnd.nextInt(MAX_SHIPMENT_QUANTITY-MIN_SHIPMENT_QUANTITY));	//Ships a random amount between the thresholds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
