package com.danielacedo.psp;

import java.util.Random;

public class Withdrawal extends Thread{
	public static final int WITHDRAWAL_PERIOD = 2400;
	public static final int MIN_WITHDRAWAL_QUANTITY = 2000;
	public static final int MAX_WITHDRAWAL_QUANTITY = 2500;
	
	private Storage storage;
		
	public Withdrawal(Storage storage){
		this.storage = storage;
	}
	
	@Override
	public void run(){
		Random rnd = new Random();
		
		while(!storage.getError())
		try {
			System.out.println("Day "+ storage.getDays());
			sleep(WITHDRAWAL_PERIOD);
			storage.withdrawStock(MIN_WITHDRAWAL_QUANTITY + rnd.nextInt(MAX_WITHDRAWAL_QUANTITY-MIN_WITHDRAWAL_QUANTITY));
			storage.setDays(storage.getDays()+1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
