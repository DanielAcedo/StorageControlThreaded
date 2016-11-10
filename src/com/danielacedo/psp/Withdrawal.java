package com.danielacedo.psp;

import java.util.Random;

/**
 * Class representing a withdrawal of stock from the storage to the factory
 * @author Daniel Acedo Calderón
 *
 */
public class Withdrawal extends Thread{
	public static final int WITHDRAWAL_PERIOD = 2400;			//How often the withdrawal will occur
	public static final int MIN_WITHDRAWAL_QUANTITY = 2000;		//Minimum amount of stock withdrawn
	public static final int MAX_WITHDRAWAL_QUANTITY = 2500;		//Maximum amount of stock withdrawn	
	
	private Storage storage;									//Shared object
		
	public Withdrawal(Storage storage){
		this.storage = storage;
	}
	
	
	@Override
	public void run(){
		Random rnd = new Random();
		
		//Keep running as long as there are no errors
		while(!storage.getError())
		try {
			System.out.println("\nDay "+ storage.getDays());
			System.out.println("------------------");
			sleep(WITHDRAWAL_PERIOD);	//Wait the defined period
			storage.withdrawStock(MIN_WITHDRAWAL_QUANTITY + rnd.nextInt(MAX_WITHDRAWAL_QUANTITY-MIN_WITHDRAWAL_QUANTITY));	//Withdraw a random amount between the thresholds
			storage.setDays(storage.getDays()+1);	//Increment the day by one as the withdrawal happens once a day
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
