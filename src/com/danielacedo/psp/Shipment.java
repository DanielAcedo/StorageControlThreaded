package com.danielacedo.psp;

import java.util.Random;

public class Shipment extends Thread{
	public static final int SHIPMENT_PERIOD = 800;
	public static final int MIN_SHIPMENT_QUANTITY = 400;
	public static final int MAX_SHIPMENT_QUANTITY = 1000;
	
	Storage storage;
	
	public Shipment(Storage storage){
		this.storage = storage;
	}
	
	@Override
	public void run(){
		Random rnd = new Random();
		
		while(!storage.getError()){
			try {
				sleep(SHIPMENT_PERIOD);
				storage.receiveStock(MIN_SHIPMENT_QUANTITY + rnd.nextInt(MAX_SHIPMENT_QUANTITY-MIN_SHIPMENT_QUANTITY));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
